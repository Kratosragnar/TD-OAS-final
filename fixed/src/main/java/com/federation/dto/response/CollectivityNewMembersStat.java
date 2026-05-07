package com.federation.dto.response;

import lombok.Data;
import java.util.UUID;

@Data
public class CollectivityNewMembersStat {
    private UUID collectivityId;
    private String collectivityName;
    private long newMembersCount;
}
