package com.federation.dto.request;

import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
public class CollectivityRequest {
    private String location;
    private String specialty;
    private List<UUID> members;
    private Boolean federationApproval;
    private CreateCollectivityStructureRequest structure;
}