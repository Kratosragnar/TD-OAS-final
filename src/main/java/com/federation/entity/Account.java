<<<<<<< HEAD
package com.federation.entity;

import com.federation.enums.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
=======

package com.federation.entity;

import jakarta.persistence.*;
import lombok.*;
>>>>>>> d7e79cd (Fourth commit)

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
<<<<<<< HEAD
@Table(name = "accounts", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"type", "collectivity_id"})
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
=======
@Table(name = "accounts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "account_type", discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Account {
>>>>>>> d7e79cd (Fourth commit)

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

<<<<<<< HEAD
    @Column(nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private AccountType type;

    @Column(precision = 15, scale = 2)
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "collectivity_id")
    private Collectivity collectivity;

    @OneToMany(mappedBy = "account")
    private List<Payment> payments = new ArrayList<>();
=======
    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collectivity_id", nullable = false)
    private Collectivity collectivity;

    @OneToMany(mappedBy = "accountCredited")
    private List<MemberPayment> receivedPayments = new ArrayList<>();

    @OneToMany(mappedBy = "accountCredited")
    private List<CollectivityTransaction> transactions = new ArrayList<>();
>>>>>>> d7e79cd (Fourth commit)
}