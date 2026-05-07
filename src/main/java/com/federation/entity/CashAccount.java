
package com.federation.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("CASH")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CashAccount extends Account {
}