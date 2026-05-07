// CreateMemberPaymentRequest.java
package com.federation.dto.request;

import com.federation.enums.PaymentMode;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class CreateMemberPaymentRequest {
    private BigDecimal amount;
    private String membershipFeeIdentifier;
    private String accountCreditedIdentifier;
    private PaymentMode paymentMode;
}