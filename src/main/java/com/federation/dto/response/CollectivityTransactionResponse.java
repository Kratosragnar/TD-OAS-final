// CollectivityTransactionResponse.java
package com.federation.dto.response;

import com.federation.enums.PaymentMode;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class CollectivityTransactionResponse {
    private UUID id;
    private LocalDate creationDate;
    private BigDecimal amount;
    private PaymentMode paymentMode;
    private UUID accountCreditedId;
    private UUID memberDebitedId;
}