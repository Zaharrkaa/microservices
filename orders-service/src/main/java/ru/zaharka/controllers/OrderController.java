package ru.zaharka.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zaharka.dto.OrderDto;
import ru.zaharka.services.OrderServiceProducer;


@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderServiceProducer orderServiceProducer;


    @PostMapping("/send")
    public ResponseEntity<OrderDto> sendOrder(@RequestBody OrderDto orderDto) {
        orderServiceProducer.send(orderDto);
        return ResponseEntity.ok(orderDto);
    }
}
