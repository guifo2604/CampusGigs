package com.fiap.campus_gigs.models;

import com.fiap.campus_gigs.enuns.GigStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Table(name = "TB_SERVICE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Gig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id", nullable = false)
    private User provider;

    private String title;

    private String description;

    private String category;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private GigStatus status = GigStatus.ACTIVE;
}