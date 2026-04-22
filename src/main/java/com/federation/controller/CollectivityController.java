package com.federation.controller;

import com.federation.dto.*;
import com.federation.service.CollectivityService;
import com.federation.service.CollectivityTransactionService;
import com.federation.service.MembershipFeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/collectivities")
@RequiredArgsConstructor
public class CollectivityController {

    private final CollectivityService collectivityService;
    private final MembershipFeeService membershipFeeService;
    private final CollectivityTransactionService transactionService;

    // PUT /collectivities/{id}/informations
    @PutMapping("/{id}/informations")
    public ResponseEntity<CollectivityResponse> updateInformations(
            @PathVariable UUID id,
            @Valid @RequestBody CollectivityInformationRequest request) {
        CollectivityResponse response = collectivityService.updateInformations(id, request);
        return ResponseEntity.ok(response);
    }

    // GET /collectivities/{id}/membershipFees
    @GetMapping("/{id}/membershipFees")
    public ResponseEntity<List<MembershipFeeResponse>> getMembershipFees(@PathVariable UUID id) {
        return ResponseEntity.ok(membershipFeeService.getFeesByCollectivity(id));
    }

    // POST /collectivities/{id}/membershipFees
    @PostMapping("/{id}/membershipFees")
    public ResponseEntity<List<MembershipFeeResponse>> createMembershipFees(
            @PathVariable UUID id,
            @Valid @RequestBody List<CreateMembershipFeeRequest> requests) {
        List<MembershipFeeResponse> created = membershipFeeService.createFees(id, requests);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // GET /collectivities/{id}/transactions
    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<CollectivityTransactionResponse>> getTransactions(
            @PathVariable UUID id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return ResponseEntity.ok(transactionService.getTransactions(id, from, to));
    }
}