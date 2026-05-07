package com.federation.dto.response;

import com.federation.enums.CollectivityStatus;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class CollectivityResponse {
    private UUID id;
    private String number;
    private String name;
    private String city;
    private String specialty;
    private LocalDate creationDate;
    private Boolean validatedByFederation;
    private CollectivityStatus status;
    private List<MemberResponse> members;
    private CollectivityStructureResponse structure;
}