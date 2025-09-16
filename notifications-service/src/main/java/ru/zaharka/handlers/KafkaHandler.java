package ru.zaharka.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.zaharka.dto.OrderDto;
import ru.zaharka.dto.UserDto;
import ru.zaharka.services.JWTService;
import ru.zaharka.services.MailSenderService;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KafkaHandler {

    private final ObjectMapper objectMapper;
    private final MailSenderService mailSenderService;
    private final RestTemplate restTemplate;
    private final static String TOPIC = "shipped_orders";
    private final JWTService jwtService;

    @KafkaListener(topics = TOPIC)
    public void handleEvent(String json) throws JsonProcessingException {
        OrderDto orderDto = objectMapper.readValue(json, OrderDto.class);
        String token = jwtService.generateToken("notifications-service");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> userJson = restTemplate.exchange("http://localhost:8090/auth/" + orderDto.getCustomerId(), HttpMethod.GET, entity, String.class);
        UserDto userDto = objectMapper.readValue(userJson.getBody(), UserDto.class);
        mailSenderService.sendMail(userDto.getEmail(), "Уведомление о заказе", "Заказ " + orderDto.getOrderId() + " был был успешно доставлен");


    }
}
