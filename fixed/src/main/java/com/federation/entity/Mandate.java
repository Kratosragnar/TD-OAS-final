package com.federation.entity;

import com.federation.enums.MemberOccupation;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "mandates")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mandate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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
