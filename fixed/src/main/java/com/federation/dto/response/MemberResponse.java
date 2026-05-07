package com.federation.dto.response;

import com.federation.enums.Gender;
import com.federation.enums.MemberStatus;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class MemberResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String fullName;
    private LocalDate birthDate;
    private Gender gender;
    private String email;
    private String phone;
    private String address;
    private LocalDate joinDate;
    private MemberStatus status;
    private UUID collectivityId;
    private String collectivityName;
    private List<SponsorResponse> referees;
}