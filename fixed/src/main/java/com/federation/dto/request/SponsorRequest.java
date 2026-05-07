// SponsorRequest.java
package com.federation.dto.request;

import lombok.Data;
import java.util.UUID;

@Data
public class SponsorRequest {
    private UUID sponsorId;
    private String relationType;
    private String relationDetails;
}