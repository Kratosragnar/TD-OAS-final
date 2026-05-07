
package com.federation.entity;

import com.federation.enums.PaymentMode;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "member_payments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "membership_fee_id")
    private MembershipFee membershipFee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_credited_id", nullable = false)
    private Account accountCredited;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_mode", nullable = false)
    private PaymentMode paymentMode;

    @CreationTimestamp
    @Column(name = "creation_date", updatable = false)
    private LocalDate creationDate;
}