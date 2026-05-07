package com.federation.controller;

import com.federation.dto.response.StatisticsResponse;
import com.federation.service.StatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/statistics")
@RequiredArgsConstructor
@Tag(name = "Statistiques", description = "Calcul des statistiques")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/collectivities/{id}")
    @Operation(summary = "Statistiques d'une collectivité")
    public ResponseEntity<StatisticsResponse.CollectivityStats> getCollectivityStats(@PathVariable UUID id) {
        return ResponseEntity.ok(statisticsService.getCollectivityStats(id));
    }

    @GetMapping("/members/{id}")
    @Operation(summary = "Statistiques d'un membre")
    public ResponseEntity<StatisticsResponse.MemberStats> getMemberStats(@PathVariable UUID id) {
        return ResponseEntity.ok(statisticsService.getMemberStats(id));
    }
}