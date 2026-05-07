package com.federation.entity;

import com.federation.enums.Gender;
import com.federation.enums.MemberOccupation;
import com.federation.enums.MemberStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @Column(name = "adhesion_date")
    private LocalDate adhesionDate;

    @ManyToOne
    @JoinColumn(name = "collectivity_id")
    private Collectivity collectivity;

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

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberPayment> payments = new ArrayList<>();

    @OneToMany(mappedBy = "memberDebited", cascade = CascadeType.ALL)
    private List<CollectivityTransaction> transactions = new ArrayList<>();

    public String getFullName() {
        return (firstName != null ? firstName : "") + " " + (lastName != null ? lastName : "");
    }

    public LocalDate getJoinDate() {
        return adhesionDate;
    }

    public String getPhone() {
        return phoneNumber;
    }

    public Integer getSeniorityDays() {
        if (adhesionDate == null) return 0;
        return (int) ChronoUnit.DAYS.between(adhesionDate, LocalDate.now());
    }
}
