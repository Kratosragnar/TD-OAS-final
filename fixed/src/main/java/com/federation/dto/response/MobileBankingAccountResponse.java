// MobileBankingAccountResponse.java
package com.federation.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MobileBankingAccountResponse extends FinancialAccountResponse {
    private String holderName;
    private String mobileBankingService;
    private String mobileNumber;

    public MobileBankingAccountResponse() {
        setType("MOBILE_MONEY");
    }
}