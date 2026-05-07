package com.federation.service;

<<<<<<< HEAD
import com.federation.dto.request.CollectivityRequest;
import com.federation.entity.Member;
import com.federation.entity.Role;
=======
import com.federation.entity.Member;
>>>>>>> d7e79cd (Fourth commit)
import com.federation.enums.MemberStatus;
import com.federation.exception.BusinessException;
import com.federation.repository.MemberRepository;
import com.federation.repository.PaymentRepository;
<<<<<<< HEAD
import com.federation.repository.RoleRepository;
=======
>>>>>>> d7e79cd (Fourth commit)
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
=======
>>>>>>> d7e79cd (Fourth commit)
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ValidationService {

    private final MemberRepository memberRepository;
    private final PaymentRepository paymentRepository;
<<<<<<< HEAD
    private final RoleRepository roleRepository;

    private static final BigDecimal ADMISSION_FEE = new BigDecimal("50000");
    private static final int MIN_SENIORITY_DAYS = 90;
    private static final int MIN_FOUNDING_MEMBERS = 10;
    private static final int MIN_MONTHS_SENIORITY = 6;

    /**
     * Valide les conditions de création d'une collectivité
     */
    public void validateCollectivityCreation(CollectivityRequest request) {
        List<String> errors = new ArrayList<>();

        // 1. Vérifier le nombre minimum de membres fondateurs
        if (request.getFoundingMembers().size() < MIN_FOUNDING_MEMBERS) {
            errors.add(String.format("Minimum %d membres fondateurs requis, %d fournis",
                    MIN_FOUNDING_MEMBERS, request.getFoundingMembers().size()));
        }

        // 2. Vérifier l'ancienneté de chaque fondateur (≥ 6 mois)
        for (var foundingMember : request.getFoundingMembers()) {
            Member member = memberRepository.findById(foundingMember.getMemberId())
                    .orElseThrow(() -> new BusinessException("Membre non trouvé: " + foundingMember.getMemberId()));

            if (member.getJoinDate() == null) {
                errors.add("Le membre " + member.getFullName() + " n'a pas de date d'adhésion");
                continue;
            }

            long monthsSinceJoin = ChronoUnit.MONTHS.between(member.getJoinDate(), LocalDate.now());
            if (monthsSinceJoin < MIN_MONTHS_SENIORITY) {
                errors.add(String.format("Le membre %s a moins de %d mois d'ancienneté (%d mois)",
                        member.getFullName(), MIN_MONTHS_SENIORITY, monthsSinceJoin));
            }
        }

        // 3. Vérifier que les postes obligatoires sont remplis
        List<String> mandatoryRoles = List.of("PRESIDENT", "TRESORIER", "SECRETAIRE");
        List<String> filledRoles = new ArrayList<>();

        for (var mandate : request.getMandates()) {
            Role role = roleRepository.findById(mandate.getRoleId())
                    .orElseThrow(() -> new BusinessException("Rôle non trouvé: " + mandate.getRoleId()));
            filledRoles.add(role.getName().toUpperCase());
        }

        for (String mandatoryRole : mandatoryRoles) {
            if (!filledRoles.contains(mandatoryRole)) {
                errors.add("Le poste de " + mandatoryRole + " doit être pourvu");
            }
        }

        if (!errors.isEmpty()) {
            throw new BusinessException("Validation échouée: " + String.join(", ", errors));
        }
    }

    /**
     * Valide les conditions d'admission d'un membre
     */
    @Transactional(readOnly = true)
    public void validateMemberAdmission(UUID memberId) {
        List<String> errors = new ArrayList<>();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException("Membre non trouvé: " + memberId));

        // 1. Vérifier le parrain
        if (member.getSponsor() == null) {
            errors.add("Un parrain est obligatoire");
        } else {
            Member sponsor = member.getSponsor();
            if (sponsor.getJoinDate() == null) {
                errors.add("Le parrain n'a pas de date d'adhésion");
            } else {
                long daysSinceJoin = ChronoUnit.DAYS.between(sponsor.getJoinDate(), LocalDate.now());
                if (daysSinceJoin < MIN_SENIORITY_DAYS) {
                    errors.add(String.format("Le parrain doit avoir au moins %d jours d'ancienneté (actuellement %d jours)",
                            MIN_SENIORITY_DAYS, daysSinceJoin));
=======

    private static final BigDecimal ADMISSION_FEE = new BigDecimal("50000");
    private static final int MIN_SENIORITY_DAYS = 90;

    @Transactional(readOnly = true)
    public void validateMemberAdmission(UUID memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException("Membre non trouvé: " + memberId));

        StringBuilder errors = new StringBuilder();

        if (member.getReferees() == null || member.getReferees().isEmpty()) {
            errors.append("Au moins un parrain est obligatoire. ");
        } else {
            for (Member sponsor : member.getReferees()) {
                if (sponsor.getAdhesionDate() == null) {
                    errors.append("Le parrain ").append(sponsor.getFullName()).append(" n'a pas de date d'adhésion. ");
                } else {
                    long daysSinceJoin = ChronoUnit.DAYS.between(sponsor.getAdhesionDate(), LocalDate.now());
                    if (daysSinceJoin < MIN_SENIORITY_DAYS) {
                        errors.append(String.format("Le parrain %s doit avoir au moins %d jours d'ancienneté (actuellement %d jours). ",
                                sponsor.getFullName(), MIN_SENIORITY_DAYS, daysSinceJoin));
                    }
>>>>>>> d7e79cd (Fourth commit)
                }
            }
        }

<<<<<<< HEAD
        // 2. Vérifier le paiement de 50 000 MGA
        boolean hasValidPayment = paymentRepository.existsByMemberIdAndAmountGreaterThanEqual(
                memberId, ADMISSION_FEE);
        if (!hasValidPayment) {
            errors.add(String.format("Le paiement de %s MGA est requis", ADMISSION_FEE));
        }

        // 3. Vérifier les informations complètes
        if (member.getFirstName() == null || member.getFirstName().isBlank()) {
            errors.add("Le prénom est obligatoire");
        }
        if (member.getLastName() == null || member.getLastName().isBlank()) {
            errors.add("Le nom est obligatoire");
        }
        if (member.getBirthDate() == null) {
            errors.add("La date de naissance est obligatoire");
        }
        if (member.getPhone() == null || member.getPhone().isBlank()) {
            errors.add("Le téléphone est obligatoire");
        }
        if (member.getAddress() == null || member.getAddress().isBlank()) {
            errors.add("L'adresse est obligatoire");
        }

        if (!errors.isEmpty()) {
            throw new BusinessException("Validation échouée: " + String.join(", ", errors));
=======
        boolean hasValidPayment = paymentRepository.existsByMemberIdAndAmountGreaterThanEqual(memberId, ADMISSION_FEE);
        if (!hasValidPayment) {
            errors.append(String.format("Le paiement de %s MGA est requis. ", ADMISSION_FEE));
        }

        if (member.getFirstName() == null || member.getFirstName().isBlank()) {
            errors.append("Le prénom est obligatoire. ");
        }
        if (member.getLastName() == null || member.getLastName().isBlank()) {
            errors.append("Le nom est obligatoire. ");
        }
        if (member.getBirthDate() == null) {
            errors.append("La date de naissance est obligatoire. ");
        }
        if (member.getPhoneNumber() == null || member.getPhoneNumber().isBlank()) {
            errors.append("Le téléphone est obligatoire. ");
        }
        if (member.getAddress() == null || member.getAddress().isBlank()) {
            errors.append("L'adresse est obligatoire. ");
        }

        if (errors.length() > 0) {
            throw new BusinessException("Validation échouée: " + errors);
>>>>>>> d7e79cd (Fourth commit)
        }

        log.info("Validation d'admission réussie pour le membre: {}", member.getFullName());
    }

<<<<<<< HEAD
    /**
     * Vérifie si un membre peut être parrain
     */
    public boolean canBeSponsor(UUID memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member == null || member.getJoinDate() == null) {
            return false;
        }

        long daysSinceJoin = ChronoUnit.DAYS.between(member.getJoinDate(), LocalDate.now());
        return daysSinceJoin >= MIN_SENIORITY_DAYS && member.getStatus() == MemberStatus.ACTIVE;
    }
}
=======
    public boolean canBeSponsor(UUID memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member == null || member.getAdhesionDate() == null) {
            return false;
        }
        long daysSinceJoin = ChronoUnit.DAYS.between(member.getAdhesionDate(), LocalDate.now());
        return daysSinceJoin >= MIN_SENIORITY_DAYS && member.getStatus() == MemberStatus.ACTIVE;
    }
}
>>>>>>> d7e79cd (Fourth commit)
