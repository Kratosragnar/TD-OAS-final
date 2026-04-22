package com.federation.dto;

import com.federation.enums.Gender;
import com.federation.enums.MemberOccupation;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class MemberDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Gender gender;
    private String address;
    private String profession;
    private String phoneNumber;
    private String email;
    private MemberOccupation occupation;
    private LocalDate adhesionDate;
    private UUID collectivityId;
    private List<UUID> refereeIds;
}