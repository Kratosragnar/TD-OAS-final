package com.federation.controller;

import com.federation.dto.request.MemberRequest;
import com.federation.dto.response.MemberResponse;
import com.federation.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@Tag(name = "Membres", description = "Gestion des membres")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    @Operation(summary = "Créer un membre")
    public ResponseEntity<MemberResponse> create(@Valid @RequestBody MemberRequest request) {
        MemberResponse response = memberService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/{id}/join")
    @Operation(summary = "Faire adhérer un membre")
    public ResponseEntity<MemberResponse> join(@PathVariable UUID id) {
        MemberResponse response = memberService.join(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/transfer")
    @Operation(summary = "Transférer un membre vers une autre collectivité")
    public ResponseEntity<MemberResponse> transfer(
            @PathVariable UUID id,
            @RequestParam UUID collectivityId) {
        MemberResponse response = memberService.transfer(id, collectivityId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/leave")
    @Operation(summary = "Faire quitter un membre")
    public ResponseEntity<MemberResponse> leave(@PathVariable UUID id) {
        MemberResponse response = memberService.leave(id);
        return ResponseEntity.ok(response);
    }
}