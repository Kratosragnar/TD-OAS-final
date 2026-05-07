package com.federation.controller;

import com.federation.dto.request.AttendanceRequest;
import com.federation.dto.response.AttendanceResponse;
import com.federation.service.AttendanceService;
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
@RequestMapping("/api/v1/attendances")
@RequiredArgsConstructor
@Tag(name = "Présences", description = "Gestion des présences aux activités")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    @Operation(summary = "Enregistrer une présence")
    public ResponseEntity<AttendanceResponse> create(@Valid @RequestBody AttendanceRequest request) {
        AttendanceResponse response = attendanceService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/activities/{activityId}")
    @Operation(summary = "Présences d'une activité")
    public ResponseEntity<List<AttendanceResponse>> findByActivity(@PathVariable UUID activityId) {
        return ResponseEntity.ok(attendanceService.findByActivityId(activityId));
    }

    @GetMapping("/members/{memberId}")
    @Operation(summary = "Présences d'un membre")
    public ResponseEntity<List<AttendanceResponse>> findByMember(@PathVariable UUID memberId) {
        return ResponseEntity.ok(attendanceService.findByMemberId(memberId));
    }
}