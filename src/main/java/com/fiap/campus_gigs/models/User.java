package com.fiap.campus_gigs.models;
import com.fiap.campus_gigs.enuns.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_USER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    @Column(name = "password_hash")
    private String passwordHash;
    @Column(name = "zip_code")
    private String zipCode;
    private String city;
    private String state;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

}