package com.zzagaechi.auth.controller;

import com.zzagaechi.auth.dto.LoginDto;
import com.zzagaechi.auth.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 */
@Tag(name = "로그인!")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginDto.LoginResponse> login(
            @RequestBody LoginDto.LoginRequest request) {
        LoginDto.LoginResponse response = authService.saveUser(request.getUid());
        return ResponseEntity.ok(response);
    }
}





