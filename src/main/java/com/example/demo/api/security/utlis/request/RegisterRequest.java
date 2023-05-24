package com.example.demo.api.security.utlis.request;

import com.example.demo.store.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String lastname;
    private String username;
    private String email;
    private String password;
    private Role role;
}