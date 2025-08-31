package ru.zaharka.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback/orders")
public class FallbackController {

    @PostMapping()
    public ResponseEntity<String> fallback() {
        return ResponseEntity.ok("Service is busy now, try later");
    }

}
