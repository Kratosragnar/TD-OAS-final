package com.federation.repository;

import com.federation.entity.Mandate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface MandateRepository extends JpaRepository<Mandate, UUID> {

    List<Mandate> findByMemberId(UUID memberId);

    List<Mandate> findByCollectivityId(UUID collectivityId);

    @Query("SELECT m FROM Mandate m WHERE m.member.id = :memberId AND m.endDate >= :today")
    List<Mandate> findActiveMandatesByMemberId(@Param("memberId") UUID memberId, @Param("today") LocalDate today);

    @Query("SELECT m FROM Mandate m WHERE m.collectivity.id = :collectivityId AND m.endDate >= :today")
    List<Mandate> findActiveMandatesByCollectivityId(@Param("collectivityId") UUID collectivityId, @Param("today") LocalDate today);
}
