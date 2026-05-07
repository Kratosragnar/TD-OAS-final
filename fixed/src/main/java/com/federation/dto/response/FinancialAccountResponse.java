package com.federation.dto.response;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import java.math.BigDecimal;
import java.util.UUID;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CashAccountResponse.class, name = "CASH"),
        @JsonSubTypes.Type(value = MobileBankingAccountResponse.class, name = "MOBILE_MONEY"),
        @JsonSubTypes.Type(value = BankAccountResponse.class, name = "BANK")
})
@Data
public abstract class FinancialAccountResponse {
    private UUID id;
    private BigDecimal amount;
    private String type;
}
