package ru.zaharka.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback/orders")
public class FallbackController {

    @GetMapping()
    public ResponseEntity<String> fallback() {
        return ResponseEntity.ok("Service is busy now, try later");
    }

}
