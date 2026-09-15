package com.fiap.campus_gigs.services;

import com.fiap.campus_gigs.enuns.Role;
import com.fiap.campus_gigs.models.User;
import com.fiap.campus_gigs.dtos.LoginRequest;
import com.fiap.campus_gigs.dtos.RegisterRequest;
import com.fiap.campus_gigs.exceptions.EmailAlreadyExistsException;
import com.fiap.campus_gigs.exceptions.InvalidCredentialsException;
import com.fiap.campus_gigs.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException(request.email());
        }

        User user = User.builder()
                .name(request.name())
                .email(request.email())
                .passwordHash(passwordEncoder.encode(request.password()))
                .zipCode(request.zipCode())
                .role(Role.USER)
                .build();

        return userRepository.save(user);
    }

    public User authenticate(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(InvalidCredentialsException::new);

        if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
            throw new InvalidCredentialsException();
        }

        return user;
    }
}