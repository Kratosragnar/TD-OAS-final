package com.federation.dto.response;

<<<<<<< HEAD
import com.federation.enums.MemberStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
=======
import com.federation.enums.Gender;
import com.federation.enums.MemberStatus;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
>>>>>>> d7e79cd (Fourth commit)
public class MemberResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String fullName;
    private LocalDate birthDate;
<<<<<<< HEAD
=======
    private Gender gender;
>>>>>>> d7e79cd (Fourth commit)
    private String email;
    private String phone;
    private String address;
    private LocalDate joinDate;
    private MemberStatus status;
<<<<<<< HEAD
    private Integer seniorityDays;
    private UUID collectivityId;
    private String collectivityName;
    private UUID sponsorId;
    private String sponsorName;
=======
    private UUID collectivityId;
    private String collectivityName;
    private List<SponsorResponse> referees;
>>>>>>> d7e79cd (Fourth commit)
}