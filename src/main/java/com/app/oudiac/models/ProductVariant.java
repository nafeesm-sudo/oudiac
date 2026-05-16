package com.app.oudiac.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class ProductVariant extends BaseModel {
    @ManyToOne
    private Product product;

    private String variantType; //100ml , 50ml,

    private BigDecimal price;
    private Long stock;

    @Column(unique = true, nullable = false)
    private String sku;  //Stock keeping unit

}
