package com.app.oudiac.models;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem extends BaseModel {


    // 🔗 Order reference
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    // 🔗 Product reference
    @ManyToOne
    @JoinColumn(name = "product_varient_id")
    private ProductVariant product;

    private int quantity;

    // 💰 Price at time of purchase
    private BigDecimal price;
}
