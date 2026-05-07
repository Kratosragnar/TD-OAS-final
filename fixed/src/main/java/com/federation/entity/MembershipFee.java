
package com.federation.entity;

import com.federation.enums.ActivityStatus;
import com.federation.enums.Frequency;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "membership_fees")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MembershipFee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collectivity_id", nullable = false)
    private Collectivity collectivity;

    @Column(name = "eligible_from", nullable = false)
    private LocalDate eligibleFrom;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Frequency frequency;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private String label;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ActivityStatus status = ActivityStatus.ACTIVE;
}