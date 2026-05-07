package com.federation.dto.response;

import lombok.Data;
import java.util.UUID;

@Data
public class SponsorResponse {
    private UUID sponsorId;
    private String sponsorName;
    private String relationType;
    private String relationDetails;
}