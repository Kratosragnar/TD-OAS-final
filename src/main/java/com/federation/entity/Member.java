package com.federation.entity;

import com.federation.enums.MemberStatus;
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
@Table(name = "members")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(unique = true, length = 100)
    private String email;

    @Column(unique = true, nullable = false, length = 20)
    private String phone;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(name = "join_date")
    private LocalDate joinDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private MemberStatus status = MemberStatus.INACTIVE;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "collectivity_id")
    private Collectivity collectivity;

    @ManyToOne
    @JoinColumn(name = "sponsor_id")
    private Member sponsor;

    @OneToMany(mappedBy = "member")
    private List<Payment> payments = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Mandate> mandates = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Attendance> attendances = new ArrayList<>();

    public Integer getSeniorityDays() {
        if (joinDate == null) return 0;
        return (int) java.time.temporal.ChronoUnit.DAYS.between(joinDate, LocalDate.now());
    }

    public Object getFullName() {
        return null;
    }

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<Sponsorship> sponsorshipsReceived = new ArrayList<>();

    @OneToMany(mappedBy = "sponsor")
    private List<Sponsorship> sponsorshipsGiven = new ArrayList<>();
}