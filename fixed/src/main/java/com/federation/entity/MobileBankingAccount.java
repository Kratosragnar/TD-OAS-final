package com.federation.entity;

import com.federation.enums.MobileBankingService;
import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("MOBILE_MONEY")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class MobileBankingAccount extends Account {

    private String holderName;

    @Enumerated(EnumType.STRING)
    private MobileBankingService mobileBankingService;

    private String mobileNumber;
}