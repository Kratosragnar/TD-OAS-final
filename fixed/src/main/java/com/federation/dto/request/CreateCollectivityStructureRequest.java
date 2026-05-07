package com.federation.dto.request;

import lombok.Data;
import java.util.UUID;

@Data
public class CreateCollectivityStructureRequest {
    private UUID president;
    private UUID vicePresident;
    private UUID treasurer;
    private UUID secretary;
}