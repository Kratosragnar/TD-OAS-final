package com.federation.service;

import com.federation.dto.response.CollectivityTransactionResponse;
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
        return transactionRepository.findByCollectivityIdAndDateBetween(collectivityId, from, to).stream().map(tx -> {
            CollectivityTransactionResponse r = new CollectivityTransactionResponse();
            r.setId(tx.getId());
            r.setCreationDate(tx.getCreationDate());
            r.setAmount(tx.getAmount());
            r.setPaymentMode(tx.getPaymentMode());
            r.setAccountCreditedId(tx.getAccountCredited().getId());
            r.setMemberDebitedId(tx.getMemberDebited().getId());
            return r;
        }).toList();
    }
}
