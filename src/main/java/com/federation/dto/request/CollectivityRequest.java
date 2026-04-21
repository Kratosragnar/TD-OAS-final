package com.federation.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class CollectivityRequest {

    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 100, message = "Le nom ne peut pas dépasser 100 caractères")
    private String name;

    @NotBlank(message = "La ville est obligatoire")
    @Size(max = 100, message = "La ville ne peut pas dépasser 100 caractères")
    private String city;

    @Size(max = 100, message = "La spécialité ne peut pas dépasser 100 caractères")
    private String specialty;

    @NotNull(message = "La date de création est obligatoire")
    private LocalDate creationDate;

    @NotNull(message = "La liste des membres fondateurs est obligatoire")
    @Size(min = 10, message = "Minimum 10 membres fondateurs requis")
    private List<FoundingMember> foundingMembers;

    @NotNull(message = "Les mandats sont obligatoires")
    private List<MandateRequest> mandates;

    @Data
    public static class FoundingMember {
        @NotNull(message = "L'ID du membre est obligatoire")
        private UUID memberId;
    }

    @Data
    public static class MandateRequest {
        @NotNull(message = "L'ID du membre est obligatoire")
        private UUID memberId;

        @NotNull(message = "L'ID du rôle est obligatoire")
        private UUID roleId;
    }
}