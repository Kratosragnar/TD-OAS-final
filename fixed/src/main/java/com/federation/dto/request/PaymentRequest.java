package com.federation.dto.request;

import com.federation.enums.PaymentType;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class PaymentRequest {

    @NotNull(message = "L'ID du membre est obligatoire")
    private UUID memberId;

    @NotNull(message = "L'ID du compte est obligatoire")
    private UUID accountId;

    @NotNull(message = "Le montant est obligatoire")
    @DecimalMin(value = "0.01", message = "Le montant doit être supérieur à 0")
    private BigDecimal amount;

    @NotNull(message = "Le type de paiement est obligatoire")
    private PaymentType type;

    @NotNull(message = "La date de paiement est obligatoire")
    @PastOrPresent(message = "La date de paiement ne peut pas être dans le futur")
    private LocalDate paymentDate;

    @Size(max = 50, message = "La référence ne peut pas dépasser 50 caractères")
    private String reference;

    private String description;
}