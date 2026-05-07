package com.federation.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class MemberRequest {

    @NotBlank(message = "Le prénom est obligatoire")
    @Size(max = 50, message = "Le prénom ne peut pas dépasser 50 caractères")
    private String firstName;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 50, message = "Le nom ne peut pas dépasser 50 caractères")
    private String lastName;

    @NotNull(message = "La date de naissance est obligatoire")
    @Past(message = "La date de naissance doit être dans le passé")
    private LocalDate birthDate;

    @Email(message = "Email invalide")
    private String email;

    @NotBlank(message = "Le téléphone est obligatoire")
    @Pattern(regexp = "^[0-9+\\- ]{10,20}$", message = "Numéro de téléphone invalide")
    private String phone;

    @NotBlank(message = "L'adresse est obligatoire")
    private String address;

    private UUID collectivityId;

    private UUID sponsorId;
}