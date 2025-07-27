package ru.zaharka.services;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.zaharka.dto.PaymentDto;

@Service
@RequiredArgsConstructor
public class PaymentServiceProducer {

    private final KafkaTemplate<String, PaymentDto> kafkaTemplate;
    private static final String topic = "payed_orders";


    public void send(PaymentDto paymentDto) {
        System.out.println("Sending: " + paymentDto);
        kafkaTemplate.send(topic, paymentDto);
    }
}
