package com.federation.dto.response;

import lombok.Data;
import java.util.List;

@Data
public class FederationStatsResponse {
    private String period;
    private List<CollectivityUpToDateStat> upToDateStats;
    private List<CollectivityNewMembersStat> newMembersStats;
}
