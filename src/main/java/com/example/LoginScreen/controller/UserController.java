package com.example.LoginScreen.controller;

import com.example.LoginScreen.dto.LoginRequest;
import com.example.LoginScreen.model.User;
import com.example.LoginScreen.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody LoginRequest loginRequest) {
        boolean isRegistered = userService.register(loginRequest);
        if (isRegistered) {
            return ResponseEntity.ok("Kullanıcı başarıyla kayıt edildi");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Kullanıcı adı zaten mevcut");
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        Optional<User> user = userService.authenticate(loginRequest);
        if (user.isPresent()) {
            return "Giriş yapıldı";
        }
        return "Kayıt bulunamadı";
    }
}
