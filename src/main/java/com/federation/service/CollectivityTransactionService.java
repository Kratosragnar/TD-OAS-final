package com.federation.service;

import com.federation.dto.response.CollectivityTransactionResponse;
import com.federation.entity.CollectivityTransaction;
import com.federation.repository.CollectivityTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CollectivityTransactionService {

    private final CollectivityTransactionRepository transactionRepository;

    public List<CollectivityTransactionResponse> getTransactions(UUID collectivityId, LocalDate from, LocalDate to) {
        return transactionRepository.findByCollectivityIdAndDateBetween(collectivityId, from, to).stream()
                .map(this::mapToResponse).toList();
    }

    private CollectivityTransactionResponse mapToResponse(CollectivityTransaction tx) {
        CollectivityTransactionResponse resp = new CollectivityTransactionResponse();
        resp.setId(tx.getId());
        resp.setCreationDate(tx.getCreationDate());
        resp.setAmount(tx.getAmount());
        resp.setPaymentMode(tx.getPaymentMode());
        resp.setAccountCreditedId(tx.getAccountCredited().getId());
        resp.setMemberDebitedId(tx.getMemberDebited().getId());
        return resp;
    }
}