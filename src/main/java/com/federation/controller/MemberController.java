package com.federation.controller;

<<<<<<< HEAD
import com.federation.dto.request.MemberRequest;
import com.federation.dto.response.MemberResponse;
import com.federation.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
=======
import com.federation.dto.request.CreateMemberRequest;
import com.federation.dto.response.MemberResponse;
import com.federation.service.MemberService;
>>>>>>> d7e79cd (Fourth commit)
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
import java.util.UUID;
=======
import java.util.List;
>>>>>>> d7e79cd (Fourth commit)

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
<<<<<<< HEAD
@Tag(name = "Membres", description = "Gestion des membres")
=======
>>>>>>> d7e79cd (Fourth commit)
public class MemberController {

    private final MemberService memberService;

    @PostMapping
<<<<<<< HEAD
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
=======
    public ResponseEntity<List<MemberResponse>> create(@Valid @RequestBody List<CreateMemberRequest> requests) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.createMembers(requests));
    }
}
>>>>>>> d7e79cd (Fourth commit)
