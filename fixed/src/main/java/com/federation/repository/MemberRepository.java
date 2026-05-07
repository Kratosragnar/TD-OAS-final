package com.federation.repository;

import com.federation.entity.Member;
import com.federation.enums.MemberStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {
    List<Member> findByCollectivityId(UUID collectivityId);

    @Query("SELECT COUNT(m) FROM Member m WHERE m.collectivity.id = :collectivityId")
    Long countByCollectivityId(@Param("collectivityId") UUID collectivityId);

    @Query("SELECT COUNT(m) FROM Member m WHERE m.collectivity.id = :collectivityId AND m.status = :status")
    Long countByCollectivityIdAndStatus(@Param("collectivityId") UUID collectivityId, @Param("status") MemberStatus status);

    @Query("SELECT m FROM Member m WHERE m.adhesionDate <= :date")
    List<Member> findMembersWithMinSeniority(@Param("date") LocalDate date);

    @Query("SELECT m FROM Member m WHERE m.adhesionDate BETWEEN :start AND :end")
    List<Member> findNewMembersBetween(@Param("start") LocalDate start, @Param("end") LocalDate end);
}
