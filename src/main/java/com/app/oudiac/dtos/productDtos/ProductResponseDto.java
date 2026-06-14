package com.app.oudiac.dtos.productDtos;

import com.app.oudiac.dtos.brandDtos.BrandResponseDto;
import com.app.oudiac.dtos.categoryDtos.CategoryResponseDto;
import com.app.oudiac.models.*;
import com.app.oudiac.models.enums.ProductStatus;
import com.app.oudiac.models.enums.ProductType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;

    private BigDecimal sellingPrice;
    private BigDecimal MRP;
    private String sku;
    private Long quantity;
    private ProductStatus productStatus;
    private String variantType;
    private String imageUrl;

    private BrandResponseDto brand;
    private CategoryResponseDto category;
    private ProductType productType;
    private String fragranceFamily;
    private List<Review> reviews;

    private Date createdAt;
    private Date updatedAt;


    public static ProductResponseDto fromProductToProductResponseDto(ProductVariant productVariant) {
        Product product= productVariant.getProduct();
        ProductResponseDto response=new ProductResponseDto();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setBrand(BrandResponseDto.fromBrandToBrandResponseDto(product.getBrand()));
        response.setCategory(CategoryResponseDto.fromCategoryToCategoryResponseDto(product.getCategory()));
        response.setProductType(product.getProductType());
        response.setFragranceFamily(product.getFragranceFamily());

        response.setSellingPrice(productVariant.getSellingPrice());
        response.setMRP(productVariant.getMRP());
        response.setSku(productVariant.getSku());
        response.setQuantity(productVariant.getStock());
        response.setProductStatus(productVariant.getProductStatus());
        response.setVariantType(productVariant.getVariantType());
        response.setCreatedAt(productVariant.getCreated_at());
        response.setUpdatedAt(productVariant.getUpdated_at());
        response.setImageUrl(product.getImageUrl());

        //Set review if required
        return response;
    }
}
