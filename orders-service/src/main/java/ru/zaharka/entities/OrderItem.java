package ru.zaharka.entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_item")
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String itemId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    private String productId;
    private int quantity;
    private double price;
}
