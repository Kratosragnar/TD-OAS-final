package com.federation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CollectivityInformationRequest {

    @NotBlank(message = "Le nom est obligatoire")
    private String name;

    @NotBlank(message = "Le numéro est obligatoire")
    private String number;   // String pour permettre des formats comme "COL-2026-001"
}