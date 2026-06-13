package com.app.oudiac.dtos.productDtos;

import com.app.oudiac.dtos.brandDtos.BrandRequestDto;
import com.app.oudiac.dtos.categoryDtos.CategoryRequestDto;
import com.app.oudiac.models.Brand;
import com.app.oudiac.models.Product;
import com.app.oudiac.models.ProductVariant;
import com.app.oudiac.models.enums.ProductStatus;
import com.app.oudiac.models.enums.ProductType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductRequestDto {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull
    private BigDecimal sellingPrice;

    @NotNull
    private BigDecimal MRP;

    @NotNull
    private String sku;

    @NotNull
    private Long quantity;

//    @NotNull
//    private String imageUrl;

    @NotNull
    private ProductStatus productStatus;

    @NotNull
    private String variantType;  //100ml, 50 ml

//    @NotBlank(message = "Brand is required")
    private String brandName;

//    @NotBlank(message = "Category is required")
    private String categoryName;

    @NotNull
    private ProductType productType;  //Attar , Bhakhoon

//    @NotBlank(message = "Fragrance family is required")
    private String fragranceFamily;

    public static ProductVariant fromProductRequestDtoToProductVariant(ProductRequestDto requestDto) {
        Product newProduct=new Product();
        newProduct.setName(requestDto.getName());
        newProduct.setDescription(requestDto.getDescription());

        BrandRequestDto brandRequestDto=new BrandRequestDto();
        brandRequestDto.setName(requestDto.getBrandName());
        newProduct.setBrand(BrandRequestDto.fromBrandRequestDtoBrand(brandRequestDto));

        CategoryRequestDto categoryRequestDto=new CategoryRequestDto();
        categoryRequestDto.setName(requestDto.getCategoryName());
        newProduct.setCategory(CategoryRequestDto.fromCategoryRequestDtoToCategory(categoryRequestDto));

        newProduct.setProductType(requestDto.getProductType());
        newProduct.setFragranceFamily(requestDto.getFragranceFamily());

        Date date=new Date();
        newProduct.setCreated_at(date);
        newProduct.setUpdated_at(date);
        newProduct.setIsDeleted(false);

        ProductVariant newProductVariant=new ProductVariant();
        newProductVariant.setProduct(newProduct);
        newProductVariant.setProductStatus(requestDto.getProductStatus());
        newProductVariant.setSku(requestDto.getSku());
        newProductVariant.setMRP(requestDto.getMRP());
        newProductVariant.setSellingPrice(requestDto.getSellingPrice());

        newProductVariant.setVariantType(requestDto.getVariantType());
        newProductVariant.setStock(requestDto.getQuantity());

        newProductVariant.setCreated_at(date);
        newProductVariant.setUpdated_at(date);
        newProductVariant.setIsDeleted(false);

        return newProductVariant;
    }
}
