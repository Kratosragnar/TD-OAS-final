package com.federation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CollectivityInformationRequest {
    @NotBlank(message = "Le numéro est obligatoire")
    @Size(max = 50)
    private String number;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 100)
    private String name;
}
