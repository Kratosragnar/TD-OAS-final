package com.federation.repository;

import com.federation.entity.Payment;
import com.federation.enums.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    List<Payment> findByMemberId(UUID memberId);

    List<Payment> findByMemberIdOrderByPaymentDateDesc(UUID memberId);

    List<Payment> findByAccountId(UUID accountId);

    List<Payment> findByType(PaymentType type);

    List<Payment> findByPaymentDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Payment p WHERE p.member.id = :memberId AND p.amount >= :amount")
    boolean existsByMemberIdAndAmountGreaterThanEqual(@Param("memberId") UUID memberId, @Param("amount") BigDecimal amount);

    @Query("SELECT COALESCE(SUM(p.amount), 0) FROM Payment p WHERE p.member.id = :memberId")
    BigDecimal sumPaymentsByMemberId(@Param("memberId") UUID memberId);

    @Query("SELECT COALESCE(SUM(p.amount), 0) FROM Payment p WHERE p.account.collectivity.id = :collectivityId")
    BigDecimal sumPaymentsByCollectivityId(@Param("collectivityId") UUID collectivityId);

    @Query("SELECT p FROM Payment p WHERE p.member.id = :memberId AND p.type = :type")
    List<Payment> findByMemberIdAndType(@Param("memberId") UUID memberId, @Param("type") PaymentType type);

    @Query("SELECT COUNT(p) FROM Payment p WHERE p.member.id = :memberId")
    Long countPaymentsByMemberId(@Param("memberId") UUID memberId);
    @Query("SELECT p FROM Payment p WHERE p.account.collectivity.id = :collectivityId")
    List<Payment> findByCollectivityId(@Param("collectivityId") UUID collectivityId);
}