package com.app.oudiac.dtos.productDtos;

import com.app.oudiac.dtos.brandDtos.BrandResponseDto;
import com.app.oudiac.dtos.categoryDtos.CategoryResponseDto;
import com.app.oudiac.models.Brand;
import com.app.oudiac.models.Category;
import com.app.oudiac.models.Product;
import com.app.oudiac.models.Review;
import com.app.oudiac.models.enums.ProductType;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private BrandResponseDto brand;
    private CategoryResponseDto category;
    private ProductType productType;
    private String fragranceFamily;
    private List<Review> reviews;

    public static ProductResponseDto fromProductToProductResponseDto(Product product) {
        ProductResponseDto response=new ProductResponseDto();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setBrand(BrandResponseDto.fromBrandToBrandResponseDto(product.getBrand()));
        response.setCategory(CategoryResponseDto.fromCategoryToCategoryResponseDto(product.getCategory()));
        response.setProductType(product.getProductType());
        response.setFragranceFamily(product.getFragranceFamily());

        //Set review if required
        return response;
    }
}
