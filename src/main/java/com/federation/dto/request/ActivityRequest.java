package com.federation.dto.request;

import com.federation.enums.ActivityType;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ActivityRequest {

    @NotBlank(message = "Le titre est obligatoire")
    @Size(max = 200, message = "Le titre ne peut pas dépasser 200 caractères")
    private String title;

    private String description;

    @NotNull(message = "La date de l'activité est obligatoire")
    @Future(message = "La date de l'activité doit être dans le futur")
    private LocalDateTime activityDate;

    @NotNull(message = "Le type d'activité est obligatoire")
    private ActivityType type;

    @NotNull(message = "L'ID de la collectivité est obligatoire")
    private UUID collectivityId;
}