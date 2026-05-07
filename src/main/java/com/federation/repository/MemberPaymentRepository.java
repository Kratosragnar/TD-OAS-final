// MemberPaymentRepository.java
package com.federation.repository;

import com.federation.entity.MemberPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MemberPaymentRepository extends JpaRepository<MemberPayment, UUID> {
    List<MemberPayment> findByMemberIdAndCreationDateBetween(UUID memberId, LocalDate start, LocalDate end);

    @Query("SELECT mp FROM MemberPayment mp WHERE mp.member.collectivity.id = :collectivityId AND mp.creationDate BETWEEN :start AND :end")
    List<MemberPayment> findByCollectivityIdAndPeriod(@Param("collectivityId") UUID collectivityId, @Param("start") LocalDate start, @Param("end") LocalDate end);
}