package ru.zaharka.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.zaharka.dto.OrderDto;
import ru.zaharka.dto.PaymentDto;
import ru.zaharka.services.ShippingServiceProducer;

@Service
@RequiredArgsConstructor
public class KafkaHandler {
    private final ObjectMapper mapper;
    private static final String TOPIC = "payed_orders";
    private final ShippingServiceProducer shippingServiceProducer;

    @KafkaListener(topics = TOPIC)
    public void handleEvent(String json) throws JsonProcessingException {
        OrderDto orderDto = mapper.readValue(json, OrderDto.class);
        System.out.println("Получены данные об оплате: " + orderDto);
        System.out.println("Упаковываем и отгружаем...");
        shippingServiceProducer.send(orderDto);
        System.out.println("Готово!");
    }
}
