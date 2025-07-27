package ru.zaharka.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaymentDto {

    private String id;
    private double amount;
    private String currency;
    private String shippingAddress;

    @Override
    public String toString() {
        return "PaymentDto{" +
                "amount=" + amount +
                ", id='" + id + '\'' +
                ", currency='" + currency + '\'' +
                ", shippingAddress='" + shippingAddress + '\'' +
                '}';
    }
}
