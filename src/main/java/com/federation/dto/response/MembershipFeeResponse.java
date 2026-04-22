package com.federation.dto.response;

import com.federation.enums.ActivityStatus;
import com.federation.enums.Frequency;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class MembershipFeeResponse {
    private UUID id;
    private LocalDate eligibleFrom;
    private Frequency frequency;
    private BigDecimal amount;
    private String label;
    private ActivityStatus status;
}