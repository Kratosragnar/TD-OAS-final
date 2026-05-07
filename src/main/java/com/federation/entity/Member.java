package com.federation.entity;

<<<<<<< HEAD
import com.federation.enums.MemberStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
=======
import com.federation.enums.Gender;
import com.federation.enums.MemberOccupation;
import com.federation.enums.MemberStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
>>>>>>> d7e79cd (Fourth commit)
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

<<<<<<< HEAD
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
=======
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
>>>>>>> d7e79cd (Fourth commit)

    @ManyToOne
    @JoinColumn(name = "collectivity_id")
    private Collectivity collectivity;

<<<<<<< HEAD
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
}
=======
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
>>>>>>> d7e79cd (Fourth commit)
