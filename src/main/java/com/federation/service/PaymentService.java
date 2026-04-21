package com.federation.service;

import com.federation.dto.request.PaymentRequest;
import com.federation.dto.response.PaymentResponse;
import com.federation.entity.Account;
import com.federation.entity.Member;
import com.federation.entity.Payment;
import com.federation.exception.BusinessException;
import com.federation.exception.ResourceNotFoundException;
import com.federation.mapper.PaymentMapper;
import com.federation.repository.AccountRepository;
import com.federation.repository.MemberRepository;
import com.federation.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final MemberRepository memberRepository;
    private final AccountRepository accountRepository;
    private final PaymentMapper paymentMapper;

    @Transactional
    public PaymentResponse create(PaymentRequest request) {
        log.info("Enregistrement d'un paiement de {} MGA pour le membre: {}", request.getAmount(), request.getMemberId());

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new ResourceNotFoundException("Membre non trouvé: " + request.getMemberId()));

        Account account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("Compte non trouvé: " + request.getAccountId()));

        // Vérifier que le compte appartient à la même collectivité que le membre
        if (member.getCollectivity() != null && account.getCollectivity() != null &&
                !member.getCollectivity().getId().equals(account.getCollectivity().getId())) {
            throw new BusinessException("Le compte n'appartient pas à la collectivité du membre");
        }

        Payment payment = paymentMapper.toEntity(request);
        payment.setMember(member);
        payment.setAccount(account);

        // Mettre à jour le solde du compte
        account.setBalance(account.getBalance().add(request.getAmount()));
        accountRepository.save(account);

        Payment savedPayment = paymentRepository.save(payment);
        log.info("Paiement enregistré avec succès: ID={}, Montant={}", savedPayment.getId(), savedPayment.getAmount());

        return paymentMapper.toResponse(savedPayment);
    }

    @Transactional(readOnly = true)
    public List<PaymentResponse> findAll() {
        return paymentRepository.findAll().stream()
                .map(paymentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PaymentResponse> findByMemberId(UUID memberId) {
        return paymentRepository.findByMemberIdOrderByPaymentDateDesc(memberId).stream()
                .map(paymentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BigDecimal getTotalPaymentsByMember(UUID memberId) {
        return paymentRepository.sumPaymentsByMemberId(memberId);
    }
}