package com.federation.repository;

import com.federation.entity.Member;
import com.federation.enums.MemberStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {

    List<Member> findByStatus(MemberStatus status);

    List<Member> findByCollectivityId(UUID collectivityId);

    Optional<Member> findByEmail(String email);

    Optional<Member> findByPhone(String phone);

    @Query("SELECT m FROM Member m WHERE m.sponsor.id = :sponsorId")
    List<Member> findSponsoredMembers(@Param("sponsorId") UUID sponsorId);

    @Query("SELECT m FROM Member m WHERE m.joinDate <= :date")
    List<Member> findMembersWithSeniorityBefore(@Param("date") LocalDate date);

    @Query("SELECT COUNT(p) FROM Member m JOIN m.payments p WHERE m.id = :memberId")
    Long countPaymentsByMemberId(@Param("memberId") UUID memberId);

    @Query("SELECT COALESCE(SUM(p.amount), 0) FROM Member m JOIN m.payments p WHERE m.id = :memberId")
    java.math.BigDecimal sumPaymentsByMemberId(@Param("memberId") UUID memberId);
}