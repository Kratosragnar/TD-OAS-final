package com.federation.dto.response;

import com.federation.enums.CollectivityStatus;
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
    private String name;
    private String city;
    private String specialty;
    private LocalDate creationDate;
    private Boolean validatedByFederation;
    private CollectivityStatus status;
    private Integer memberCount;
    private Long activeMandatesCount;
}