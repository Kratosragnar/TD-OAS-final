package com.federation.controller;

import com.federation.dto.request.AccountRequest;
import com.federation.dto.response.AccountResponse;
import com.federation.service.AccountService;
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
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
@Tag(name = "Comptes", description = "Gestion des comptes financiers")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    @Operation(summary = "Créer un compte")
    public ResponseEntity<AccountResponse> create(@Valid @RequestBody AccountRequest request) {
        AccountResponse response = accountService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Lister tous les comptes")
    public ResponseEntity<List<AccountResponse>> findAll() {
        return ResponseEntity.ok(accountService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un compte par son ID")
    public ResponseEntity<AccountResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(accountService.findById(id));
    }

    @GetMapping("/collectivities/{collectivityId}")
    @Operation(summary = "Comptes d'une collectivité")
    public ResponseEntity<List<AccountResponse>> findByCollectivity(@PathVariable UUID collectivityId) {
        return ResponseEntity.ok(accountService.findByCollectivityId(collectivityId));
    }
}