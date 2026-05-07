package com.federation.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class CollectivityStatsResponse {
    private UUID collectivityId;
    private String collectivityName;
    private List<MemberPaymentStats> memberStats;
}
