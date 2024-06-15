package com.procesos.parcial.controller;

import com.procesos.parcial.model.User;
import com.procesos.parcial.model.dto.AuthRequest;
import com.procesos.parcial.model.dto.AuthResponse;
import com.procesos.parcial.model.dto.Response;
import com.procesos.parcial.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest){
        return ResponseEntity.ok(service.login(authRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<Response> register(@Valid @RequestBody User registerRequest) {
        return ResponseEntity.ok(service.register(registerRequest));
    }

}
