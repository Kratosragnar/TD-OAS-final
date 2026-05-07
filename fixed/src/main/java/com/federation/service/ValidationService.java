package com.federation.service;

import com.federation.entity.Member;
import com.federation.enums.MemberStatus;
import com.federation.exception.BusinessException;
import com.federation.repository.MemberRepository;
import com.federation.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ValidationService {

    private final MemberRepository memberRepository;
    private final PaymentRepository paymentRepository;

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
                }
            }
        }

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
        }

        log.info("Validation d'admission réussie pour le membre: {}", member.getFullName());
    }

    public boolean canBeSponsor(UUID memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member == null || member.getAdhesionDate() == null) {
            return false;
        }
        long daysSinceJoin = ChronoUnit.DAYS.between(member.getAdhesionDate(), LocalDate.now());
        return daysSinceJoin >= MIN_SENIORITY_DAYS && member.getStatus() == MemberStatus.ACTIVE;
    }
}
