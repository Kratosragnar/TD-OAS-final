package com.federation.entity;

<<<<<<< HEAD
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

=======
import com.federation.enums.MemberOccupation;
import jakarta.persistence.*;
import lombok.*;
>>>>>>> d7e79cd (Fourth commit)
import java.time.LocalDate;
import java.util.UUID;

@Entity
<<<<<<< HEAD
@Table(name = "mandates", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"member_id", "role_id", "collectivity_id"})
})
=======
@Table(name = "mandates")
>>>>>>> d7e79cd (Fourth commit)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mandate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

<<<<<<< HEAD
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collectivity_id", nullable = false)
    private Collectivity collectivity;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "is_active")
    private Boolean isActive = true;
}
=======
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "collectivity_id", nullable = false)
    private Collectivity collectivity;

    @Enumerated(EnumType.STRING)
    private MemberOccupation role;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    public Boolean getIsActive() {
        return endDate != null && !LocalDate.now().isAfter(endDate);
    }
}
>>>>>>> d7e79cd (Fourth commit)
