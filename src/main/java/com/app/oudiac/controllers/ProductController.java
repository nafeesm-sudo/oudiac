package com.app.oudiac.controllers;

import com.app.oudiac.dtos.productDtos.ProductRequestDto;
import com.app.oudiac.dtos.productDtos.ProductResponseDto;
import com.app.oudiac.models.Product;
import com.app.oudiac.services.productService.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    @PostMapping(value = "/oudiac/add",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductResponseDto> addProduct(@Valid @ModelAttribute ProductRequestDto requestDto,@RequestParam("images") MultipartFile[] images){
        return productService.addProduct(requestDto);
    }

//    start with getProducts with Pagination
    @GetMapping("/getAllProducts")
    public Page<Product> getProducts(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "5") int size){
        return productService.getProducts(page,size);
    }
}
