package com.app.oudiac.services.productService;

import com.app.oudiac.dtos.productDtos.ProductRequestDto;
import com.app.oudiac.dtos.productDtos.ProductResponseDto;
import com.app.oudiac.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface IProductService {
    ResponseEntity<ProductResponseDto> addProduct(ProductRequestDto requestDto);

    Page<Product> getProducts(Integer page, Integer size);
}
