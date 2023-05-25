package com.example.backend.api.security.servise;

import com.example.backend.api.security.utlis.request.AuthenticationRequest;
import com.example.backend.api.security.jwtconfig.JwtService;
import com.example.backend.api.security.utlis.request.RegisterRequest;
import com.example.backend.api.security.utlis.response.AuthenticationResponse;
import com.example.backend.store.entities.Role;
import com.example.backend.store.entities.UserEntity;
import com.example.backend.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var userRegister = UserEntity.builder()
                .lastname(request.getLastname())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(Role.ADMIN)
                .build();

        userRepository.save(userRegister);

        var jwtToken = jwtService.generaleToken(userRegister);

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByUsername(request.getUsername()).orElseThrow();

        var jwtToken = jwtService.generaleToken(user);

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }
}