package com.federation.controller;

import com.federation.dto.request.ActivityRequest;
import com.federation.dto.response.ActivityResponse;
import com.federation.service.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/activities")
@RequiredArgsConstructor
@Tag(name = "Activités", description = "Gestion des activités")
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping
    @Operation(summary = "Créer une activité")
    public ResponseEntity<ActivityResponse> create(@Valid @RequestBody ActivityRequest request) {
        ActivityResponse response = activityService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Lister toutes les activités")
    public ResponseEntity<List<ActivityResponse>> findAll() {
        return ResponseEntity.ok(activityService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir une activité par son ID")
    public ResponseEntity<ActivityResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(activityService.findById(id));
    }

    @GetMapping("/collectivities/{collectivityId}")
    @Operation(summary = "Activités d'une collectivité")
    public ResponseEntity<List<ActivityResponse>> findByCollectivity(@PathVariable UUID collectivityId) {
        return ResponseEntity.ok(activityService.findByCollectivityId(collectivityId));
    }
}