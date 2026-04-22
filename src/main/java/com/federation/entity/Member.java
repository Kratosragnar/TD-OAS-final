package com.federation.entity;

import com.federation.enums.Gender;
import com.federation.enums.MemberOccupation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String address;
    private String profession;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String email;

    @Enumerated(EnumType.STRING)
    private MemberOccupation occupation;

    @Column(name = "adhesion_date")
    private LocalDate adhesionDate;

    @ManyToOne
    @JoinColumn(name = "collectivity_id")
    private Collectivity collectivity;

    // Parrains : relation réflexive ManyToMany
    @ManyToMany
    @JoinTable(
            name = "member_referees",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "referee_id")
    )
    private List<Member> referees = new ArrayList<>();

    @Column(name = "mandate_start")
    private LocalDate mandateStart;

    @Column(name = "mandate_end")
    private LocalDate mandateEnd;

    // Paiements effectués par le membre
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberPayment> payments = new ArrayList<>();

    // Transactions dans lesquelles le membre est débité
    @OneToMany(mappedBy = "memberDebited", cascade = CascadeType.ALL)
    private List<CollectivityTransaction> transactions = new ArrayList<>();
}