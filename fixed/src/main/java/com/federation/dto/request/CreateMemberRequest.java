package com.federation.dto.request;

import com.federation.enums.Gender;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class CreateMemberRequest {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Gender gender;
    private String address;
    private String profession;
    private String phoneNumber;
    private String email;
    private UUID collectivityId;
    private List<SponsorRequest> referees;
}