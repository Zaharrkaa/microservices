package ru.zaharka.services;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.zaharka.dto.OrderDto;

@Service
@RequiredArgsConstructor
public class OrderServiceProducer {
    private static final String orderTopic = "new_orders";
    private final KafkaTemplate<String, OrderDto> kafkaTemplate;


    public void send(OrderDto orderDto) {
        System.out.println("sending: " + orderDto);
        kafkaTemplate.send(orderTopic, orderDto);
    }

}
