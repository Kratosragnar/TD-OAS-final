package com.federation.dto.request;

import com.federation.enums.AccountType;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.UUID;

@Data
public class AccountRequest {

    @NotBlank(message = "Le nom du compte est obligatoire")
    @Size(max = 100, message = "Le nom ne peut pas dépasser 100 caractères")
    private String name;

    @NotNull(message = "Le type de compte est obligatoire")
    private AccountType type;

    @NotNull(message = "L'ID de la collectivité est obligatoire")
    private UUID collectivityId;
}