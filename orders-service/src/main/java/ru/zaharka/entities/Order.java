package ru.zaharka.entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "order")
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String orderId;

    private String customerId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;

    private double totalAmount;

    private String status;

    private String shippingAddress;
}
