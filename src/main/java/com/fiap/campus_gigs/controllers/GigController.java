package com.fiap.campus_gigs.controllers;

import com.fiap.campus_gigs.dtos.GigCreateRequest;
import com.fiap.campus_gigs.dtos.GigResponse;
import com.fiap.campus_gigs.dtos.GigUpdateRequest;
import com.fiap.campus_gigs.enuns.GigStatus;
import com.fiap.campus_gigs.security.SecurityUser;
import com.fiap.campus_gigs.services.GigService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class GigController {

    private final GigService gigService;

    // Publicar: qualquer usuário autenticado (ADMIN ou USER) pode publicar.
    @PostMapping
    public ResponseEntity<GigResponse> publish(
            @AuthenticationPrincipal SecurityUser principal,
            @Valid @RequestBody GigCreateRequest request
    ) {
        var gig = gigService.publish(principal.getUser(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(GigResponse.from(gig));
    }

    // Listar: público, não altera dado, não exige token (ver SecurityConfig).
    @GetMapping
    public ResponseEntity<List<GigResponse>> list(@RequestParam(required = false) GigStatus status) {
        var gigs = gigService.list(status).stream().map(GigResponse::from).toList();
        return ResponseEntity.ok(gigs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GigResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(GigResponse.from(gigService.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GigResponse> update(
            @AuthenticationPrincipal SecurityUser principal,
            @PathVariable Long id,
            @Valid @RequestBody GigUpdateRequest request
    ) {
        var gig = gigService.update(id, principal.getUser(), request);
        return ResponseEntity.ok(GigResponse.from(gig));
    }

    @PatchMapping("/{id}/close")
    public ResponseEntity<GigResponse> close(
            @AuthenticationPrincipal SecurityUser principal,
            @PathVariable Long id
    ) {
        var gig = gigService.close(id, principal.getUser());
        return ResponseEntity.ok(GigResponse.from(gig));
    }
}