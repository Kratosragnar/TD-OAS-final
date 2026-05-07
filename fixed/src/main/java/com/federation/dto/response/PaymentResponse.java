package com.federation.dto.response;

import com.federation.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
    private UUID id;
    private BigDecimal amount;
    private PaymentType type;
    private LocalDate paymentDate;
    private String reference;
    private String description;
    private UUID memberId;
    private String memberName;
    private UUID accountId;
    private String accountName;
}