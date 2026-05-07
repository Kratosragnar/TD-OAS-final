package com.federation.dto.response;

import lombok.Data;
import java.util.UUID;

@Data
public class CollectivityUpToDateStat {
    private UUID collectivityId;
    private String collectivityName;
    private double upToDatePercentage;
}
