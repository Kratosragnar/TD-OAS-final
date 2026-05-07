package com.federation.service;

import com.federation.dto.request.CreateMemberPaymentRequest;
import com.federation.dto.response.MemberPaymentResponse;
import com.federation.entity.*;
import com.federation.exception.*;
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
    private final MembershipFeeRepository feeRepository;
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
                fee = feeRepository.findById(UUID.fromString(req.getMembershipFeeIdentifier()))
                        .orElseThrow(() -> new ResourceNotFoundException("Cotisation non trouvée"));
            }
            MemberPayment payment = MemberPayment.builder()
                    .member(member)
                    .accountCredited(account)
                    .amount(req.getAmount())
                    .paymentMode(req.getPaymentMode())
                    .membershipFee(fee)
                    .build();
            payments.add(payment);

            CollectivityTransaction tx = CollectivityTransaction.builder()
                    .collectivity(member.getCollectivity())
                    .memberDebited(member)
                    .accountCredited(account)
                    .amount(req.getAmount())
                    .paymentMode(req.getPaymentMode())
                    .build();
            transactions.add(tx);
        }
        paymentRepository.saveAll(payments);
        transactionRepository.saveAll(transactions);

        return payments.stream().map(p -> {
            MemberPaymentResponse r = new MemberPaymentResponse();
            r.setId(p.getId());
            r.setAmount(p.getAmount());
            r.setPaymentMode(p.getPaymentMode());
            r.setAccountCreditedId(p.getAccountCredited().getId());
            r.setCreationDate(p.getCreationDate());
            return r;
        }).toList();
    }
}
