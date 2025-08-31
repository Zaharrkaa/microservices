package ru.zaharka.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.zaharka.entities.UserCredential;
import ru.zaharka.services.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String addUser(@RequestBody UserCredential userCredential){
        return authService.saveUser(userCredential);
    }

    @PostMapping("/token")
    public String getToken(@RequestBody UserCredential userCredential){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userCredential.getName(), userCredential.getPassword()));
        if(authentication.isAuthenticated()){
            return authService.generateToken(userCredential.getName());
        }
        else{
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam String token){
        authService.validateToken(token);
        return "token is valid";
    }

}
