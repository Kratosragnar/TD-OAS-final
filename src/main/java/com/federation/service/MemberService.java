package com.federation.service;

import com.federation.dto.request.MemberRequest;
import com.federation.dto.response.MemberResponse;
import com.federation.entity.Collectivity;
import com.federation.entity.Member;
import com.federation.enums.MemberStatus;
import com.federation.exception.BusinessException;
import com.federation.exception.ResourceNotFoundException;
import com.federation.mapper.MemberMapper;
import com.federation.repository.CollectivityRepository;
import com.federation.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final CollectivityRepository collectivityRepository;
    private final ValidationService validationService;
    private final MemberMapper memberMapper;

    @Transactional
    public MemberResponse create(MemberRequest request) {
        log.info("Création d'un nouveau membre: {} {}", request.getFirstName(), request.getLastName());

        // Vérifier l'unicité de l'email
        if (request.getEmail() != null && !request.getEmail().isBlank()) {
            if (memberRepository.findByEmail(request.getEmail()).isPresent()) {
                throw new BusinessException("Un membre avec cet email existe déjà");
            }
        }

        // Vérifier l'unicité du téléphone
        if (memberRepository.findByPhone(request.getPhone()).isPresent()) {
            throw new BusinessException("Un membre avec ce numéro de téléphone existe déjà");
        }

        // Créer le membre
        Member member = Member.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .birthDate(request.getBirthDate())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .status(MemberStatus.INACTIVE)
                .build();

        // Associer à une collectivité si fournie
        if (request.getCollectivityId() != null) {
            Collectivity collectivity = collectivityRepository.findById(request.getCollectivityId())
                    .orElseThrow(() -> new ResourceNotFoundException("Collectivité non trouvée avec l'ID: " + request.getCollectivityId()));
            member.setCollectivity(collectivity);
        }

        // Associer un parrain si fourni
        if (request.getSponsorId() != null) {
            Member sponsor = memberRepository.findById(request.getSponsorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Parrain non trouvé avec l'ID: " + request.getSponsorId()));

            // Vérifier que le parrain remplit les conditions
            if (!validationService.canBeSponsor(request.getSponsorId())) {
                throw new BusinessException("Le parrain ne remplit pas les conditions requises (ancienneté ≥ 90 jours et statut ACTIF)");
            }

            member.setSponsor(sponsor);
        }

        Member savedMember = memberRepository.save(member);
        log.info("Membre créé avec succès: ID={}", savedMember.getId());

        return memberMapper.toResponse(savedMember);
    }

    @Transactional
    public MemberResponse join(UUID id) {
        log.info("Demande d'adhésion pour le membre: {}", id);

        // Récupérer le membre avec ses relations
        Member member = memberRepository.findByIdWithRelations(id)
                .orElseThrow(() -> new ResourceNotFoundException("Membre non trouvé: " + id));

        // Validation des conditions d'admission
        validationService.validateMemberAdmission(id);

        // Mettre à jour le membre
        member.setJoinDate(LocalDate.now());
        member.setStatus(MemberStatus.ACTIVE);

        Member updatedMember = memberRepository.save(member);
        log.info("Membre admis avec succès: {}", id);

        return memberMapper.toResponse(updatedMember);
    }

    @Transactional
    public MemberResponse transfer(UUID id, UUID newCollectivityId) {
        log.info("Transfert du membre {} vers la collectivité {}", id, newCollectivityId);

        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Membre non trouvé: " + id));

        Collectivity newCollectivity = collectivityRepository.findById(newCollectivityId)
                .orElseThrow(() -> new ResourceNotFoundException("Collectivité non trouvée: " + newCollectivityId));

        // Vérifier que le membre est actif
        if (member.getStatus() != MemberStatus.ACTIVE) {
            throw new BusinessException("Seuls les membres actifs peuvent être transférés");
        }

        // Vérifier que ce n'est pas la même collectivité
        if (member.getCollectivity() != null && member.getCollectivity().getId().equals(newCollectivityId)) {
            throw new BusinessException("Le membre appartient déjà à cette collectivité");
        }

        member.setCollectivity(newCollectivity);
        Member updatedMember = memberRepository.save(member);

        log.info("Membre transféré avec succès: {} vers la collectivité {}", id, newCollectivityId);
        return memberMapper.toResponse(updatedMember);
    }

    @Transactional
    public MemberResponse leave(UUID id) {
        log.info("Départ du membre: {}", id);

        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Membre non trouvé: " + id));

        // Vérifier que le membre est actif
        if (member.getStatus() != MemberStatus.ACTIVE) {
            throw new BusinessException("Le membre n'est pas actif");
        }

        member.setStatus(MemberStatus.INACTIVE);
        Member updatedMember = memberRepository.save(member);

        log.info("Membre a quitté: {}", id);
        return memberMapper.toResponse(updatedMember);
    }

    @Transactional(readOnly = true)
    public MemberResponse findById(UUID id) {
        Member member = memberRepository.findByIdWithRelations(id)
                .orElseThrow(() -> new ResourceNotFoundException("Membre non trouvé: " + id));
        return memberMapper.toResponse(member);
    }

    @Transactional(readOnly = true)
    public List<MemberResponse> findAll() {
        return memberRepository.findAll().stream()
                .map(member -> {
                    // Charger manuellement les relations si nécessaire
                    Member fullMember = memberRepository.findByIdWithRelations(member.getId())
                            .orElse(member);
                    return memberMapper.toResponse(fullMember);
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MemberResponse> findByCollectivityId(UUID collectivityId) {
        return memberRepository.findByCollectivityId(collectivityId).stream()
                .map(member -> {
                    Member fullMember = memberRepository.findByIdWithRelations(member.getId())
                            .orElse(member);
                    return memberMapper.toResponse(fullMember);
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MemberResponse> findByStatus(MemberStatus status) {
        return memberRepository.findByStatus(status).stream()
                .map(member -> {
                    Member fullMember = memberRepository.findByIdWithRelations(member.getId())
                            .orElse(member);
                    return memberMapper.toResponse(fullMember);
                })
                .collect(Collectors.toList());
    }
}