package com.federation.controller;

import com.federation.dto.request.CreateMemberRequest;
import com.federation.dto.response.MemberResponse;
import com.federation.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<List<MemberResponse>> create(@Valid @RequestBody List<CreateMemberRequest> requests) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.createMembers(requests));
    }
}
