package com.federation.service;

import com.federation.dto.request.MemberRequest;
import com.federation.dto.response.MemberResponse;
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

        // Vérifier l'unicité de l'email et du téléphone
        if (request.getEmail() != null && memberRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BusinessException("Un membre avec cet email existe déjà");
        }
        if (memberRepository.findByPhone(request.getPhone()).isPresent()) {
            throw new BusinessException("Un membre avec ce numéro de téléphone existe déjà");
        }

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
            member.setCollectivity(collectivityRepository.findById(request.getCollectivityId())
                    .orElseThrow(() -> new ResourceNotFoundException("Collectivité non trouvée")));
        }

        // Associer un parrain si fourni
        if (request.getSponsorId() != null) {
            if (!validationService.canBeSponsor(request.getSponsorId())) {
                throw new BusinessException("Le parrain ne remplit pas les conditions requises");
            }
            member.setSponsor(memberRepository.findById(request.getSponsorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Parrain non trouvé")));
        }

        Member savedMember = memberRepository.save(member);
        log.info("Membre créé avec succès: ID={}", savedMember.getId());

        return memberMapper.toResponse(savedMember);
    }

    @Transactional
    public MemberResponse join(UUID id) {
        log.info("Adhésion du membre: {}", id);

        // Validation des conditions d'admission
        validationService.validateMemberAdmission(id);

        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Membre non trouvé: " + id));

        member.setJoinDate(LocalDate.now());
        member.setStatus(MemberStatus.ACTIVE);

        Member updatedMember = memberRepository.save(member);
        log.info("Membre admis avec succès: {}", id);

        return memberMapper.toResponse(updatedMember);
    }

    @Transactional
    public MemberResponse transfer(UUID id, UUID newCollectivityId) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Membre non trouvé: " + id));

        var newCollectivity = collectivityRepository.findById(newCollectivityId)
                .orElseThrow(() -> new ResourceNotFoundException("Collectivité non trouvée"));

        member.setCollectivity(newCollectivity);
        Member updatedMember = memberRepository.save(member);

        log.info("Membre transféré: {} vers la collectivité {}", id, newCollectivityId);
        return memberMapper.toResponse(updatedMember);
    }

    @Transactional
    public MemberResponse leave(UUID id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Membre non trouvé: " + id));

        member.setStatus(MemberStatus.INACTIVE);
        Member updatedMember = memberRepository.save(member);

        log.info("Membre a quitté: {}", id);
        return memberMapper.toResponse(updatedMember);
    }
}