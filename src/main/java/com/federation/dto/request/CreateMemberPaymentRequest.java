package com.federation.dto.request;

import com.federation.enums.PaymentMode;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class CreateMemberPaymentRequest {

    @NotNull(message = "Le montant est obligatoire")
    @DecimalMin(value = "0.01", message = "Le montant doit être supérieur à 0")
    private BigDecimal amount;

    // Identifiant du frais d'adhésion (UUID string), peut être null
    private String membershipFeeIdentifier;

    @NotBlank(message = "L'identifiant du compte crédité est obligatoire")
    private String accountCreditedIdentifier;

    @NotNull(message = "Le mode de paiement est obligatoire")
    private PaymentMode paymentMode;
}