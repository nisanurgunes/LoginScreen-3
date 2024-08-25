package com.example.LoginScreen.service;

import com.example.LoginScreen.dto.LoginRequest;
import com.example.LoginScreen.model.User;
import com.example.LoginScreen.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean register(LoginRequest loginRequest) {
        if (userRepository.findByUsernameandPassword(loginRequest.getUsername(), loginRequest.getPassword()).isPresent()) {
            return false;
        }
        User newUser = new User();
        newUser.setUsername(loginRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(loginRequest.getPassword())); // Hash'leme
        userRepository.save(newUser);
        return true;
    }

    public Optional<User> authenticate(LoginRequest loginRequest) {
        Optional<User> user = userRepository.findByUsernameandPassword(loginRequest.getUsername(), loginRequest.getPassword());
        if (user.isPresent() && passwordEncoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
            return user;
        }
        return Optional.empty();
    }
}
