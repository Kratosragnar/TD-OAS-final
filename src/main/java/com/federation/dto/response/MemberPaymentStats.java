package com.federation.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.util.UUID;

@Data
public class MemberPaymentStats {
    private UUID memberId;
    private String memberName;
    private BigDecimal totalPaid;
    private BigDecimal unpaids;
}
