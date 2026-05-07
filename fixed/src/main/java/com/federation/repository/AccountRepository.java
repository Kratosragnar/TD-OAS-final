package com.federation.repository;

import com.federation.entity.Account;
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
