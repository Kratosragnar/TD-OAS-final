package com.federation.repository;

import com.federation.entity.Member;
import com.federation.enums.MemberStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
<<<<<<< HEAD
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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

    // Nouvelles méthodes pour les statistiques
=======
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {
    List<Member> findByCollectivityId(UUID collectivityId);

>>>>>>> d7e79cd (Fourth commit)
    @Query("SELECT COUNT(m) FROM Member m WHERE m.collectivity.id = :collectivityId")
    Long countByCollectivityId(@Param("collectivityId") UUID collectivityId);

    @Query("SELECT COUNT(m) FROM Member m WHERE m.collectivity.id = :collectivityId AND m.status = :status")
    Long countByCollectivityIdAndStatus(@Param("collectivityId") UUID collectivityId, @Param("status") MemberStatus status);

<<<<<<< HEAD
    // Recherche avec fetch des relations pour éviter LazyInitializationException
    @Query("SELECT m FROM Member m LEFT JOIN FETCH m.collectivity LEFT JOIN FETCH m.sponsor WHERE m.id = :id")
    Optional<Member> findByIdWithRelations(@Param("id") UUID id);

    // Vérifier si un email existe déjà (hors membre courant)
    @Query("SELECT COUNT(m) > 0 FROM Member m WHERE m.email = :email AND (:excludeId IS NULL OR m.id != :excludeId)")
    boolean existsByEmailAndIdNot(@Param("email") String email, @Param("excludeId") UUID excludeId);

    // Vérifier si un téléphone existe déjà (hors membre courant)
    @Query("SELECT COUNT(m) > 0 FROM Member m WHERE m.phone = :phone AND (:excludeId IS NULL OR m.id != :excludeId)")
    boolean existsByPhoneAndIdNot(@Param("phone") String phone, @Param("excludeId") UUID excludeId);
}
=======
    @Query("SELECT m FROM Member m WHERE m.adhesionDate <= :date")
    List<Member> findMembersWithMinSeniority(@Param("date") LocalDate date);

    @Query("SELECT m FROM Member m WHERE m.adhesionDate BETWEEN :start AND :end")
    List<Member> findNewMembersBetween(@Param("start") LocalDate start, @Param("end") LocalDate end);
}
>>>>>>> d7e79cd (Fourth commit)
