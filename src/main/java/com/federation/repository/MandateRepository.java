package com.federation.repository;

import com.federation.entity.Mandate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MandateRepository extends JpaRepository<Mandate, UUID> {

    List<Mandate> findByMemberId(UUID memberId);

    List<Mandate> findByCollectivityId(UUID collectivityId);

    List<Mandate> findByIsActiveTrue();

    @Query("SELECT m FROM Mandate m WHERE m.member.id = :memberId AND m.isActive = true")
    List<Mandate> findActiveMandatesByMemberId(@Param("memberId") UUID memberId);

    @Query("SELECT m FROM Mandate m WHERE m.collectivity.id = :collectivityId AND m.isActive = true")
    List<Mandate> findActiveMandatesByCollectivityId(@Param("collectivityId") UUID collectivityId);

    boolean existsByMemberIdAndRoleIdAndCollectivityId(UUID memberId, UUID roleId, UUID collectivityId);
}