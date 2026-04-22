package com.federation.entity;

import com.federation.enums.SponsorshipRelationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "sponsorships", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"candidate_id", "sponsor_id"})
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sponsorship {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id", nullable = false)
    private Member candidate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sponsor_id", nullable = false)
    private Member sponsor;

    @Enumerated(EnumType.STRING)
    @Column(name = "relation_type", nullable = false, length = 20)
    private SponsorshipRelationType relationType;

    @Column(name = "relation_details", length = 100)
    private String relationDetails;
}