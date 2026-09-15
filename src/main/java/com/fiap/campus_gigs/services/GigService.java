package com.fiap.campus_gigs.services;

import com.fiap.campus_gigs.dtos.GigCreateRequest;
import com.fiap.campus_gigs.dtos.GigUpdateRequest;
import com.fiap.campus_gigs.enuns.GigStatus;
import com.fiap.campus_gigs.enuns.Role;
import com.fiap.campus_gigs.exceptions.ForbiddenOperationException;
import com.fiap.campus_gigs.exceptions.GigNotFoundException;
import com.fiap.campus_gigs.models.Gig;
import com.fiap.campus_gigs.models.User;
import com.fiap.campus_gigs.repositories.GigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GigService {

    private final GigRepository gigRepository;

    public Gig publish(User provider, GigCreateRequest request) {
        Gig gig = Gig.builder()
                .provider(provider)
                .title(request.title())
                .description(request.description())
                .category(request.category())
                .price(request.price())
                .status(GigStatus.ACTIVE)
                .build();

        return gigRepository.save(gig);
    }

    public List<Gig> list(GigStatus status) {
        return status == null ? gigRepository.findAll() : gigRepository.findByStatus(status);
    }

    public Gig findById(Long id) {
        return gigRepository.findById(id)
                .orElseThrow(() -> new GigNotFoundException(id));
    }

    public Gig update(Long id, User requester, GigUpdateRequest request) {
        Gig gig = findById(id);


        if (!isOwner(gig, requester)) {
            throw new ForbiddenOperationException("Only the provider who owns this service can edit it");
        }

        gig.setTitle(request.title());
        gig.setDescription(request.description());
        gig.setCategory(request.category());
        gig.setPrice(request.price());

        return gigRepository.save(gig);
    }

    public Gig close(Long id, User requester) {
        Gig gig = findById(id);

        boolean canClose = isOwner(gig, requester) || requester.getRole() == Role.ADMIN;
        if (!canClose) {
            throw new ForbiddenOperationException("Only the owner or an ADMIN can close this service");
        }

        gig.setStatus(GigStatus.CLOSED);
        return gigRepository.save(gig);
    }

    private boolean isOwner(Gig gig, User requester) {
        return gig.getProvider().getId().equals(requester.getId());
    }
}