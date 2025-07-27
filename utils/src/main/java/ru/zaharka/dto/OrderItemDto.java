package ru.zaharka.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

    private String itemId;

    private String productId;

    private int quantity;

    private double price;

    @Override
    public String toString() {
        return "OrderItemDto{" +
                "itemId='" + itemId + '\'' +
                ", productId='" + productId + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
