package ru.zaharka.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private String orderId;

    private String customerId;

    private List<OrderItemDto> orderItems;

    private double totalAmount;

    private String status;

    private String shippingAddress;


    @Override
    public String toString() {
        return "OrderDto{" +
                "customerId='" + customerId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderItems=" + orderItems +
                ", totalAmount=" + totalAmount +
                ", status='" + status + '\'' +
                ", shippingAddress='" + shippingAddress + '\'' +
                '}';
    }
}
