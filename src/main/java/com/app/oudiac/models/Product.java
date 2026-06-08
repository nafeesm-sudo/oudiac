package com.app.oudiac.models;

import com.app.oudiac.models.enums.ProductType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Product extends BaseModel{
    private String name;
    private String description;
    private String imageUrl;
    private String code;

    @ManyToOne
    private Brand brand;

    @ManyToOne
    private Category category;

    @Enumerated(value = EnumType.STRING)
    private ProductType productType;

    private String fragranceFamily;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Review> reviews;

    @OneToMany(mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    List<ProductVariant> productVariants;
}
