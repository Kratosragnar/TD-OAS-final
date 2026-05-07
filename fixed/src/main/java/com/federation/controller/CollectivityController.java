package com.federation.controller;

import com.federation.dto.request.CollectivityRequest;
import com.federation.dto.request.CollectivityInformationRequest;
import com.federation.dto.request.CreateMembershipFeeRequest;
import com.federation.dto.response.*;
import com.federation.service.*;
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

    @PostMapping
    public ResponseEntity<List<CollectivityResponse>> create(@Valid @RequestBody List<CollectivityRequest> requests) {
        return ResponseEntity.status(HttpStatus.CREATED).body(collectivityService.createCollectivities(requests));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollectivityResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(collectivityService.getCollectivityById(id));
    }

    @PutMapping("/{id}/informations")
    public ResponseEntity<CollectivityResponse> updateInformations(@PathVariable UUID id, @Valid @RequestBody CollectivityInformationRequest request) {
        return ResponseEntity.ok(collectivityService.updateInformations(id, request));
    }

    @GetMapping("/{id}/financialAccounts")
    public ResponseEntity<List<FinancialAccountResponse>> getAccounts(@PathVariable UUID id, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate at) {
        return ResponseEntity.ok(collectivityService.getFinancialAccounts(id, at));
    }

    @PostMapping("/{id}/membershipFees")
    public ResponseEntity<List<MembershipFeeResponse>> createFees(@PathVariable UUID id, @Valid @RequestBody List<CreateMembershipFeeRequest> requests) {
        return ResponseEntity.status(HttpStatus.CREATED).body(membershipFeeService.createFees(id, requests));
    }

    @GetMapping("/{id}/membershipFees")
    public ResponseEntity<List<MembershipFeeResponse>> getFees(@PathVariable UUID id) {
        return ResponseEntity.ok(membershipFeeService.getFees(id));
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<CollectivityTransactionResponse>> getTransactions(@PathVariable UUID id,
                                                                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                                                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return ResponseEntity.ok(transactionService.getTransactions(id, from, to));
    }

    @GetMapping("/{id}/statistics")
    public ResponseEntity<CollectivityStatsResponse> getStats(@PathVariable UUID id,
                                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(collectivityService.getCollectivityStats(id, startDate, endDate));
    }

    @GetMapping("/statistics")
    public ResponseEntity<FederationStatsResponse> getFederationStats(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(collectivityService.getFederationStats(startDate, endDate));
    }
}
