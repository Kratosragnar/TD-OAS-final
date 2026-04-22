package com.federation.repository;

import com.federation.entity.CollectivityTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface CollectivityTransactionRepository extends JpaRepository<CollectivityTransaction, UUID> {
    @Query("SELECT t FROM CollectivityTransaction t WHERE t.collectivity.id = :collectivityId AND t.creationDate BETWEEN :from AND :to")
    List<CollectivityTransaction> findByCollectivityIdAndDateBetween(@Param("collectivityId") UUID collectivityId,
                                                                     @Param("from") LocalDate from,
                                                                     @Param("to") LocalDate to);
}