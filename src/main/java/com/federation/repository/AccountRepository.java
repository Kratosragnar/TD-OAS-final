package com.federation.repository;

import com.federation.entity.Account;
<<<<<<< HEAD
import com.federation.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    List<Account> findByCollectivityId(UUID collectivityId);

    List<Account> findByCollectivityIdAndIsActiveTrue(UUID collectivityId);

    Optional<Account> findByCollectivityIdAndType(UUID collectivityId, AccountType type);

    boolean existsByCollectivityIdAndType(UUID collectivityId, AccountType type);

    @Query("SELECT a FROM Account a WHERE a.type = 'CASH' AND a.collectivity.id = :collectivityId")
    Optional<Account> findCashAccountByCollectivityId(@Param("collectivityId") UUID collectivityId);

    @Query("SELECT COALESCE(SUM(a.balance), 0) FROM Account a WHERE a.collectivity.id = :collectivityId")
    BigDecimal getTotalBalanceByCollectivityId(@Param("collectivityId") UUID collectivityId);
}
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    List<Account> findByCollectivityId(UUID collectivityId);

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM CashAccount a WHERE a.collectivity.id = :collectivityId")
    boolean existsCashAccountByCollectivityId(@Param("collectivityId") UUID collectivityId);
}
>>>>>>> d7e79cd (Fourth commit)
