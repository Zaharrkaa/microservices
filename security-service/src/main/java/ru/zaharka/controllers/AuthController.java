package ru.zaharka.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.zaharka.entities.UserCredential;
import ru.zaharka.services.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String addUser(@RequestBody UserCredential userCredential){
        return authService.saveUser(userCredential);
    }

    @GetMapping("/token")
    public String getToken(@RequestBody UserCredential userCredential){
        return authService.generateToken(userCredential.getName());
    }

    @GetMapping("/validate")
    public String validateToken(String token){
        authService.validateToken(token);
        return "token is valid";
    }

}
