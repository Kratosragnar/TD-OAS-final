// CashAccountResponse.java
package com.federation.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CashAccountResponse extends FinancialAccountResponse {
    public CashAccountResponse() {
        setType("CASH");
    }
}