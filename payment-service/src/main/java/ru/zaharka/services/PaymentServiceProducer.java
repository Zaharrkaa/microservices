package ru.zaharka.services;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.zaharka.dto.OrderDto;
import ru.zaharka.dto.PaymentDto;

@Service
@RequiredArgsConstructor
public class PaymentServiceProducer {

    private final KafkaTemplate<String, OrderDto> kafkaTemplate;
    private static final String TOPIC = "payed_orders";


    public void send(OrderDto orderDto) {
        System.out.println("Sending: " + orderDto);
        kafkaTemplate.send(TOPIC, orderDto);
    }
}
