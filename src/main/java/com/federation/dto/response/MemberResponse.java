package com.federation.dto.response;

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
public class MemberResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String fullName;
    private LocalDate birthDate;
    private String email;
    private String phone;
    private String address;
    private LocalDate joinDate;
    private MemberStatus status;
    private Integer seniorityDays;
    private UUID collectivityId;
    private String collectivityName;
    private UUID sponsorId;
    private String sponsorName;
}