package com.fiap.campus_gigs.repositories;

import com.fiap.campus_gigs.enuns.GigStatus;
import com.fiap.campus_gigs.models.Gig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GigRepository extends JpaRepository<Gig, Long> {
    List<Gig> findByStatus(GigStatus status);
}