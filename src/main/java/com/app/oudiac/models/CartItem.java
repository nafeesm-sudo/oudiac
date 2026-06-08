package com.app.oudiac.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CartItem extends BaseModel{
    @ManyToOne
    private Cart cart;

    @ManyToOne
    private ProductVariant product;

    private Integer quantity;
}
