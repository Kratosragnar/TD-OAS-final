package com.federation.entity;

import com.federation.enums.CollectivityStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "collectivities")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Collectivity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(length = 100)
    private String specialty;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @Column(name = "validated_by_federation")
    private Boolean validatedByFederation = false;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private CollectivityStatus status = CollectivityStatus.PENDING;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "collectivity", cascade = CascadeType.ALL)
    private List<Member> members = new ArrayList<>();

    @OneToMany(mappedBy = "collectivity", cascade = CascadeType.ALL)
    private List<Mandate> mandates = new ArrayList<>();

    @OneToMany(mappedBy = "collectivity", cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();

    @OneToMany(mappedBy = "collectivity", cascade = CascadeType.ALL)
    private List<Activity> activities = new ArrayList<>();
}