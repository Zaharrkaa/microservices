package ru.zaharka.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zaharka.entities.UserCredential;
import ru.zaharka.repos.UserCredentialRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserCredentialRepository userCredentialRepository;
    private final JWTService jwtService;

    @Transactional
    public String saveUser(UserCredential userCredential) {
        userCredential.setPassword(passwordEncoder.encode(userCredential.getPassword()));
        userCredentialRepository.save(userCredential);
        return "User added to system";
    }

    public String generateToken(String username){
        jwtService.generateToken(username);
        return jwtService.generateToken(username);
    }

    public UserCredential findUserById(int id){
        Optional<UserCredential> userCredential = userCredentialRepository.findById(id);
        if (userCredential.isPresent()) {
            return userCredential.get();
        }
        else {
            throw new RuntimeException("User not found");
        }
    }

    public void validateToken(String token) {
        jwtService.parseToken(token);
    }
}
