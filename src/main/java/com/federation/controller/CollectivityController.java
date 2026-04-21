package com.federation.controller;

import com.federation.dto.request.CollectivityRequest;
import com.federation.dto.response.CollectivityResponse;
import com.federation.service.CollectivityService;
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
@RequestMapping("/api/v1/collectivities")
@RequiredArgsConstructor
@Tag(name = "Collectivités", description = "Gestion des collectivités")
public class CollectivityController {

    private final CollectivityService collectivityService;

    @PostMapping
    @Operation(summary = "Créer une collectivité")
    public ResponseEntity<CollectivityResponse> create(@Valid @RequestBody CollectivityRequest request) {
        CollectivityResponse response = collectivityService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Lister toutes les collectivités")
    public ResponseEntity<List<CollectivityResponse>> findAll() {
        return ResponseEntity.ok(collectivityService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir une collectivité par son ID")
    public ResponseEntity<CollectivityResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(collectivityService.findById(id));
    }

    @PostMapping("/{id}/validate")
    @Operation(summary = "Valider une collectivité par la fédération")
    public ResponseEntity<Void> validate(@PathVariable UUID id) {
        collectivityService.validateByFederation(id);
        return ResponseEntity.ok().build();
    }
}