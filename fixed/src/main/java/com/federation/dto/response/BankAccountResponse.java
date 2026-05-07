package com.federation.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BankAccountResponse extends FinancialAccountResponse {
    private String holderName;
    private String bankName;
    private String bankCode;
    private String bankBranchCode;
    private String bankAccountNumber;
    private String bankAccountKey;

    public BankAccountResponse() {
        setType("BANK");
    }
}
