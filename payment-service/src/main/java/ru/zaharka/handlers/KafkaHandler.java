package ru.zaharka.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.zaharka.dto.OrderDto;
import ru.zaharka.dto.PaymentDto;
import ru.zaharka.services.PaymentServiceProducer;

@Service
@RequiredArgsConstructor
public class KafkaHandler {
    private final ObjectMapper objectMapper;
    private static final String topic = "new_orders";
    private final PaymentServiceProducer paymentServiceProducer;

    @KafkaListener(topics = topic, groupId = "payment_group")
    public void handleOrderEvent(String json) throws JsonProcessingException {
        OrderDto orderDto = objectMapper.readValue(json, OrderDto.class);
        System.out.println(orderDto);
        if (orderDto.getStatus().equals("payed")){
            System.out.println("Заказ оплачен!");
            PaymentDto paymentDto = new PaymentDto("1", orderDto.getTotalAmount(), "RUB", orderDto.getShippingAddress());
            paymentServiceProducer.send(orderDto);
        }
        else {
            System.out.println("Заказ не оплачен.");
        }
    }
}
