package com.app.oudiac.models;

import com.app.oudiac.models.enums.OrderStatus;
import com.app.oudiac.models.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
public class Order extends BaseModel{

    // 🔗 User who placed the order
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 📦 Order Items
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items;

    // 💰 Total price (calculated in service)
    private BigDecimal totalAmount;

    // 🚚 Shipping details
    private String shippingAddress;

    // 💳 Payment method (COD, ONLINE)
    private PaymentMethod paymentMethod;

    // 📌 Order status
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    private Store store;

}
