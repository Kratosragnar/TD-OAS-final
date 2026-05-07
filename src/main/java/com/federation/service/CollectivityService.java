package com.federation.service;

import com.federation.dto.request.CollectivityRequest;
import com.federation.dto.response.CollectivityResponse;
import com.federation.entity.Collectivity;
import com.federation.entity.Mandate;
import com.federation.entity.Member;
import com.federation.entity.Role;
import com.federation.enums.CollectivityStatus;
import com.federation.exception.BusinessException;
import com.federation.exception.ResourceNotFoundException;
import com.federation.mapper.CollectivityMapper;
import com.federation.repository.CollectivityRepository;
import com.federation.repository.MemberRepository;
import com.federation.repository.RoleRepository;
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
public class CollectivityService {

    private final CollectivityRepository collectivityRepository;
    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final ValidationService validationService;
    private final CollectivityMapper collectivityMapper;

    @Transactional
    public CollectivityResponse create(CollectivityRequest request) {
        log.info("Création d'une nouvelle collectivité: {}", request.getName());

        // Validation métier
        validationService.validateCollectivityCreation(request);

        // Créer la collectivité
        Collectivity collectivity = Collectivity.builder()
                .name(request.getName())
                .city(request.getCity())
                .specialty(request.getSpecialty())
                .creationDate(request.getCreationDate())
                .validatedByFederation(false)
                .status(CollectivityStatus.PENDING)
                .build();

        Collectivity savedCollectivity = collectivityRepository.save(collectivity);

        // Ajouter les membres fondateurs
        for (var foundingMember : request.getFoundingMembers()) {
            Member member = memberRepository.findById(foundingMember.getMemberId())
                    .orElseThrow(() -> new ResourceNotFoundException("Membre non trouvé"));
            member.setCollectivity(savedCollectivity);
            memberRepository.save(member);
        }

        // Créer les mandats
        for (var mandateRequest : request.getMandates()) {
            Member member = memberRepository.findById(mandateRequest.getMemberId())
                    .orElseThrow(() -> new ResourceNotFoundException("Membre non trouvé"));
            Role role = roleRepository.findById(mandateRequest.getRoleId())
                    .orElseThrow(() -> new ResourceNotFoundException("Rôle non trouvé"));

            Mandate mandate = Mandate.builder()
                    .member(member)
                    .role(role)
                    .collectivity(savedCollectivity)
                    .startDate(LocalDate.now())
                    .isActive(true)
                    .build();

            // À sauvegarder via un repository dédié (simplifié ici)
        }

        log.info("Collectivité créée avec succès: ID={}", savedCollectivity.getId());
        return collectivityMapper.toResponse(savedCollectivity);
    }

    @Transactional(readOnly = true)
    public List<CollectivityResponse> findAll() {
        return collectivityRepository.findAll().stream()
                .map(collectivityMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CollectivityResponse findById(UUID id) {
        Collectivity collectivity = collectivityRepository.findByIdWithMembers(id)
                .orElseThrow(() -> new ResourceNotFoundException("Collectivité non trouvée: " + id));
        return collectivityMapper.toResponse(collectivity);
    }

    @Transactional
    public void validateByFederation(UUID id) {
        Collectivity collectivity = collectivityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Collectivité non trouvée: " + id));

        collectivity.setValidatedByFederation(true);
        collectivity.setStatus(CollectivityStatus.ACTIVE);
        collectivityRepository.save(collectivity);

        log.info("Collectivité validée par la fédération: {}", id);
    }
}