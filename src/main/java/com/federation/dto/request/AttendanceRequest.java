package com.federation.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class AttendanceRequest {

    @NotNull(message = "L'ID de l'activité est obligatoire")
    private UUID activityId;

    @NotNull(message = "L'ID du membre est obligatoire")
    private UUID memberId;

    @NotNull(message = "Le statut de présence est obligatoire")
    private Boolean present;

    private String justification;
}