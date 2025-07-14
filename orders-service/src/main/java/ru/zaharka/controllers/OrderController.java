package ru.zaharka.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zaharka.dto.OrderDto;
import ru.zaharka.dto.OrderItemDto;
import ru.zaharka.services.OrderServiceProducer;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderServiceProducer orderServiceProducer;

    @PostMapping("/send")
    public ResponseEntity<OrderDto> sendOrder() {
        OrderItemDto orderItemDto = new OrderItemDto("1", "2", 1, 3);
        OrderDto orderDto = new OrderDto("123", "1234", List.of(orderItemDto), 5, "done", "Russia");
        orderServiceProducer.send(orderDto);
        return ResponseEntity.ok(orderDto);
    }
}
