package com.zzagaechi.auth.service;

import com.zzagaechi.auth.dto.LoginDto;
import com.zzagaechi.user.entity.User;
import com.zzagaechi.user.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final UserRepo userRepo;

    public LoginDto.LoginResponse saveUser(String uid) {
        try {
            User user = userRepo.findById(uid)
                    .orElseGet(() -> User.builder()
                            .uid(uid)
                            .build());

            userRepo.save(user);
            return LoginDto.LoginResponse.builder()
                    .check(true)
                    .build();
        } catch (Exception e) {
            log.error("User save failed", e);
            return LoginDto.LoginResponse.builder()
                    .check(false)
                    .build();
        }
    }
}
