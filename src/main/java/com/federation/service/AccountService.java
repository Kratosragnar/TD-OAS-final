package com.federation.service;

import com.federation.dto.request.AccountRequest;
import com.federation.dto.response.AccountResponse;
<<<<<<< HEAD
import com.federation.entity.Account;
import com.federation.entity.Collectivity;
=======
import com.federation.entity.*;
>>>>>>> d7e79cd (Fourth commit)
import com.federation.enums.AccountType;
import com.federation.exception.BusinessException;
import com.federation.exception.ResourceNotFoundException;
import com.federation.repository.AccountRepository;
import com.federation.repository.CollectivityRepository;
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
public class AccountService {

    private final AccountRepository accountRepository;
    private final CollectivityRepository collectivityRepository;

    @Transactional
    public AccountResponse create(AccountRequest request) {
<<<<<<< HEAD
        log.info("Création d'un compte: {} de type {}", request.getName(), request.getType());

        // Validation caisse unique
        if (request.getType() == AccountType.CASH) {
            boolean cashExists = accountRepository.existsByCollectivityIdAndType(
                    request.getCollectivityId(), AccountType.CASH);
=======
        log.info("Création d'un compte de type {}", request.getType());

        if (request.getType() == AccountType.CASH) {
            boolean cashExists = accountRepository.existsCashAccountByCollectivityId(request.getCollectivityId());
>>>>>>> d7e79cd (Fourth commit)
            if (cashExists) {
                throw new BusinessException("Une caisse existe déjà pour cette collectivité");
            }
        }

        Collectivity collectivity = collectivityRepository.findById(request.getCollectivityId())
                .orElseThrow(() -> new ResourceNotFoundException("Collectivité non trouvée: " + request.getCollectivityId()));

<<<<<<< HEAD
        Account account = Account.builder()
                .name(request.getName())
                .type(request.getType())
                .collectivity(collectivity)
                .balance(BigDecimal.ZERO)
                .isActive(true)
                .build();

=======
        Account account = buildAccount(request.getType(), collectivity);
>>>>>>> d7e79cd (Fourth commit)
        Account savedAccount = accountRepository.save(account);
        log.info("Compte créé avec succès: ID={}", savedAccount.getId());

        return toResponse(savedAccount);
    }

<<<<<<< HEAD
=======
    private Account buildAccount(AccountType type, Collectivity collectivity) {
        return switch (type) {
            case CASH -> {
                CashAccount a = new CashAccount();
                a.setCollectivity(collectivity);
                a.setBalance(BigDecimal.ZERO);
                yield a;
            }
            case BANK -> {
                BankAccount a = new BankAccount();
                a.setCollectivity(collectivity);
                a.setBalance(BigDecimal.ZERO);
                yield a;
            }
            case MOBILE_MONEY -> {
                MobileBankingAccount a = new MobileBankingAccount();
                a.setCollectivity(collectivity);
                a.setBalance(BigDecimal.ZERO);
                yield a;
            }
        };
    }

>>>>>>> d7e79cd (Fourth commit)
    @Transactional(readOnly = true)
    public List<AccountResponse> findAll() {
        return accountRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AccountResponse findById(UUID id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Compte non trouvé: " + id));
        return toResponse(account);
    }

    @Transactional(readOnly = true)
    public List<AccountResponse> findByCollectivityId(UUID collectivityId) {
        return accountRepository.findByCollectivityId(collectivityId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private AccountResponse toResponse(Account account) {
        return AccountResponse.builder()
                .id(account.getId())
<<<<<<< HEAD
                .name(account.getName())
                .type(account.getType())
                .balance(account.getBalance())
                .isActive(account.getIsActive())
=======
                .balance(account.getBalance())
>>>>>>> d7e79cd (Fourth commit)
                .collectivityId(account.getCollectivity() != null ? account.getCollectivity().getId() : null)
                .collectivityName(account.getCollectivity() != null ? account.getCollectivity().getName() : null)
                .build();
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> d7e79cd (Fourth commit)
