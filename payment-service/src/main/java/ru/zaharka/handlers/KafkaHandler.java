package ru.zaharka.handlers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.zaharka.dto.OrderDto;

@Service
public class KafkaHandler {
    private static final String topic = "new_orders";

    @KafkaListener(topics = topic, groupId = "payment_group")
    public void handleOrderEvent(OrderDto orderDto){

        System.out.println(orderDto);
    }
}
