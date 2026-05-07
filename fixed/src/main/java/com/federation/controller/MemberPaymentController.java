package com.federation.controller;

import com.federation.dto.request.CreateMemberPaymentRequest;
import com.federation.dto.response.MemberPaymentResponse;
import com.federation.service.MemberPaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/members/{id}/payments")
@RequiredArgsConstructor
public class MemberPaymentController {

    private final MemberPaymentService paymentService;

    @PostMapping
    public ResponseEntity<List<MemberPaymentResponse>> create(@PathVariable UUID id, @Valid @RequestBody List<CreateMemberPaymentRequest> requests) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.createPayments(id, requests));
    }
}