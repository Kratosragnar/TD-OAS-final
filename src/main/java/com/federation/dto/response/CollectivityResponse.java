package com.federation.dto.response;

import com.federation.enums.CollectivityStatus;
<<<<<<< HEAD
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
public class CollectivityResponse {
    private UUID id;
=======
import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class CollectivityResponse {
    private UUID id;
    private String number;
>>>>>>> d7e79cd (Fourth commit)
    private String name;
    private String city;
    private String specialty;
    private LocalDate creationDate;
    private Boolean validatedByFederation;
    private CollectivityStatus status;
<<<<<<< HEAD
    private Integer memberCount;
    private Long activeMandatesCount;
=======
    private List<MemberResponse> members;
    private CollectivityStructureResponse structure;
>>>>>>> d7e79cd (Fourth commit)
}