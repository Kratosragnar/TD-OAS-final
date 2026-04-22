package com.federation.service;

import com.federation.dto.request.CreateMemberPaymentRequest;
import com.federation.dto.response.MemberPaymentResponse;
import com.federation.entity.*;
import com.federation.exception.ResourceNotFoundException;
import com.federation.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberPaymentService {

    private final MemberPaymentRepository paymentRepository;
    private final MemberRepository memberRepository;
    private final AccountRepository accountRepository;
    private final MembershipFeeRepository membershipFeeRepository;
    private final CollectivityTransactionRepository transactionRepository;

    @Transactional
    public List<MemberPaymentResponse> createPayments(UUID memberId, List<CreateMemberPaymentRequest> requests) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Membre non trouvé"));

        List<MemberPayment> payments = new ArrayList<>();
        List<CollectivityTransaction> transactions = new ArrayList<>();

        for (CreateMemberPaymentRequest req : requests) {
            Account account = accountRepository.findById(UUID.fromString(req.getAccountCreditedIdentifier()))
                    .orElseThrow(() -> new ResourceNotFoundException("Compte non trouvé"));

            MembershipFee fee = null;
            if (req.getMembershipFeeIdentifier() != null && !req.getMembershipFeeIdentifier().isBlank()) {
                fee = membershipFeeRepository.findById(UUID.fromString(req.getMembershipFeeIdentifier()))
                        .orElseThrow(() -> new ResourceNotFoundException("Frais d'adhésion non trouvé"));
            }

            MemberPayment payment = MemberPayment.builder()
                    .member(member)
                    .membershipFee(fee)
                    .accountCredited(account)
                    .amount(req.getAmount())
                    .paymentMode(req.getPaymentMode())
                    .build();
            payments.add(payment);

            // Transaction automatique dans la collectivité
            CollectivityTransaction transaction = CollectivityTransaction.builder()
                    .collectivity(member.getCollectivity())
                    .memberDebited(member)
                    .accountCredited(account)
                    .amount(req.getAmount())
                    .paymentMode(req.getPaymentMode())
                    .build();
            transactions.add(transaction);
        }

        paymentRepository.saveAll(payments);
        transactionRepository.saveAll(transactions);

        return payments.stream().map(this::mapToResponse).toList();
    }

    private MemberPaymentResponse mapToResponse(MemberPayment payment) {
        MemberPaymentResponse resp = new MemberPaymentResponse();
        resp.setId(payment.getId());
        resp.setAmount(payment.getAmount());
        resp.setPaymentMode(payment.getPaymentMode());
        resp.setAccountCreditedId(payment.getAccountCredited().getId());
        resp.setCreationDate(payment.getCreationDate());
        return resp;
    }
}