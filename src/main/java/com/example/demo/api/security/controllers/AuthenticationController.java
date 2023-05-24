package com.example.demo.api.security.controllers;


import com.example.demo.api.security.servise.AuthenticationService;
import com.example.demo.api.security.utlis.request.AuthenticationRequest;
import com.example.demo.api.security.utlis.request.RegisterRequest;
import com.example.demo.api.security.utlis.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private static final String register = "/register";
    private static final String authenticate = "/authenticate";

    @PostMapping(register)
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping(authenticate)
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}