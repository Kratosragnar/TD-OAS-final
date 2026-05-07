package com.federation.entity;

import com.federation.enums.Bank;
import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("BANK")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class BankAccount extends Account {

    private String holderName;

    @Enumerated(EnumType.STRING)
    private Bank bankName;

    private String bankCode;
    private String bankBranchCode;
    private String bankAccountNumber;
    private String bankAccountKey;
}