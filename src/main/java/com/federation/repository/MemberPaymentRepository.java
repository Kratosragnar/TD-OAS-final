package com.federation.repository;

import com.federation.entity.MemberPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface MemberPaymentRepository extends JpaRepository<MemberPayment, UUID> {
}