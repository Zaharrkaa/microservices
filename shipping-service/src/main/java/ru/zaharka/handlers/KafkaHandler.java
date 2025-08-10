package ru.zaharka.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.zaharka.dto.PaymentDto;

@Service
@RequiredArgsConstructor
public class KafkaHandler {
    private final ObjectMapper mapper;
    private static final String TOPIC = "payed_orders";

    @KafkaListener(topics = TOPIC)
    public void handleEvent(String json) throws JsonProcessingException {
        PaymentDto paymentDto = mapper.readValue(json, PaymentDto.class);
        System.out.println("Получены данные об оплате: " + paymentDto);
        System.out.println("Упаковываем и отгружаем...");

        System.out.println("Готово!");
    }
}
