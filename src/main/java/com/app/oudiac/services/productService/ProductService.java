package com.app.oudiac.services.productService;

import com.app.oudiac.dtos.productDtos.ProductRequestDto;
import com.app.oudiac.dtos.productDtos.ProductResponseDto;
import com.app.oudiac.models.Brand;
import com.app.oudiac.models.Category;
import com.app.oudiac.models.Product;
import com.app.oudiac.repositories.BrandRepository;
import com.app.oudiac.repositories.CategoryRepository;
import com.app.oudiac.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements IProductService{

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<ProductResponseDto> addProduct(ProductRequestDto requestDto) {

        Product newProduct=ProductRequestDto.fromProductRequestDtoToProduct(requestDto);

        //Check brand availability in db if not add new brand
        Optional<Brand> brandOptional=brandRepository.findByName(requestDto.getBrand().getName());
        if(brandOptional.isEmpty()){
            brandRepository.save(newProduct.getBrand());
        }else{
            newProduct.setBrand(brandOptional.get());
        }


        //Check category availability in db if not add new category
        Optional<Category> categoryOptional=categoryRepository.findByName(requestDto.getCategory().getName());
        if(categoryOptional.isEmpty()){
            categoryRepository.save(newProduct.getCategory());
        }else{
            newProduct.setCategory(categoryOptional.get());
        }


        productRepository.save(newProduct);

        ProductResponseDto responseDto=ProductResponseDto.fromProductToProductResponseDto(newProduct);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Override
    public Page<Product> getProducts(Integer page, Integer size) {
        Page<Product> productPage = productRepository.findAll(PageRequest.of(page, size));
        return productPage;
    }


}
