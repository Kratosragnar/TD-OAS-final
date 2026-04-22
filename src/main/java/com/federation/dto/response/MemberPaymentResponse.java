package com.federation.dto.response;

import com.federation.enums.PaymentMode;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class MemberPaymentResponse {
    private UUID id;
    private BigDecimal amount;
    private PaymentMode paymentMode;
    private UUID accountCreditedId;
    private LocalDate creationDate;
}