package com.app.oudiac.dtos.productDtos;

import com.app.oudiac.dtos.brandDtos.BrandRequestDto;
import com.app.oudiac.dtos.categoryDtos.CategoryRequestDto;
import com.app.oudiac.models.Product;
import com.app.oudiac.models.enums.ProductType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class ProductRequestDto {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

//    @NotBlank(message = "Brand is required")
    private BrandRequestDto brand;

//    @NotBlank(message = "Category is required")
    private CategoryRequestDto category;

    @NotNull
    private ProductType productType;

    @NotBlank(message = "Fragrance family is required")
    private String fragranceFamily;

    public static Product fromProductRequestDtoToProduct(ProductRequestDto requestDto) {
        Product newProduct=new Product();
        newProduct.setName(requestDto.getName());
        newProduct.setDescription(requestDto.getDescription());
        newProduct.setBrand(BrandRequestDto.fromBrandRequestDtoBrand(requestDto.getBrand()));
        newProduct.setCategory(CategoryRequestDto.fromCategoryRequestDtoToCategory(requestDto.getCategory()));
        newProduct.setProductType(requestDto.getProductType());
        newProduct.setFragranceFamily(requestDto.getFragranceFamily());

        Date date=new Date();
        newProduct.setCreated_at(date);
        newProduct.setUpdated_at(date);
        newProduct.setIsDeleted(false);

        return newProduct;
    }
}
