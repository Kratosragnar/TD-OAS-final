package com.federation.controller;

import com.federation.dto.request.PaymentRequest;
import com.federation.dto.response.PaymentResponse;
import com.federation.service.PaymentService;
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
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
@Tag(name = "Paiements", description = "Gestion des paiements et cotisations")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    @Operation(summary = "Enregistrer un paiement")
    public ResponseEntity<PaymentResponse> create(@Valid @RequestBody PaymentRequest request) {
        PaymentResponse response = paymentService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Lister tous les paiements")
    public ResponseEntity<List<PaymentResponse>> findAll() {
        return ResponseEntity.ok(paymentService.findAll());
    }

    @GetMapping("/members/{memberId}")
    @Operation(summary = "Paiements d'un membre")
    public ResponseEntity<List<PaymentResponse>> findByMember(@PathVariable UUID memberId) {
        return ResponseEntity.ok(paymentService.findByMemberId(memberId));
    }
}