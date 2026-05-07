
package com.federation.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "account_type", discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collectivity_id", nullable = false)
    private Collectivity collectivity;

    @OneToMany(mappedBy = "accountCredited")
    private List<MemberPayment> receivedPayments = new ArrayList<>();

    @OneToMany(mappedBy = "accountCredited")
    private List<CollectivityTransaction> transactions = new ArrayList<>();
}