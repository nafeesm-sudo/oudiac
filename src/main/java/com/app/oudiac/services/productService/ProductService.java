package com.app.oudiac.services.productService;

import com.app.oudiac.dtos.productDtos.ProductRequestDto;
import com.app.oudiac.dtos.productDtos.ProductResponseDto;
import com.app.oudiac.models.Brand;
import com.app.oudiac.models.Category;
import com.app.oudiac.models.Product;
import com.app.oudiac.models.ProductVariant;
import com.app.oudiac.repositories.BrandRepository;
import com.app.oudiac.repositories.CategoryRepository;
import com.app.oudiac.repositories.ProductRepository;
import com.app.oudiac.repositories.ProductVariantRepository;
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

    @Autowired
    private ProductVariantRepository productVariantRepository;

    @Override
    public ResponseEntity<ProductResponseDto> addProduct(ProductRequestDto requestDto) {

        ProductVariant newProductVariant=ProductRequestDto.fromProductRequestDtoToProductVariant(requestDto);
        Product newProduct=newProductVariant.getProduct();

        //Check brand availability in db if not add new brand
        Optional<Brand> brandOptional=brandRepository.findByName(requestDto.getBrandName());
        if(brandOptional.isEmpty()){
            brandRepository.save(newProduct.getBrand());
        }else{
            newProduct.setBrand(brandOptional.get());
        }


        //Check category availability in db if not add new category
        Optional<Category> categoryOptional=categoryRepository.findByName(requestDto.getCategoryName());
        if(categoryOptional.isEmpty()){
            categoryRepository.save(newProduct.getCategory());
        }else{
            newProduct.setCategory(categoryOptional.get());
        }

        String code=getProductCode(requestDto.getSku());
        Optional<Product> productOptional=productRepository.findByCode(code);
        if(productOptional.isEmpty()){
            productRepository.save(newProduct);
        }else {
            newProductVariant.setProduct(productOptional.get());
        }

        productVariantRepository.save(newProductVariant);

        ProductResponseDto responseDto=ProductResponseDto.fromProductToProductResponseDto(newProduct);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Override
    public Page<Product> getProducts(Integer page, Integer size) {
        Page<Product> productPage = productRepository.findAll(PageRequest.of(page, size));
        return productPage;
    }

    public String getProductCode(String sku) {
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("SKU cannot be null or empty");
        }

        int lastHyphenIndex = sku.lastIndexOf('-');

        if (lastHyphenIndex <= 0) {
            throw new IllegalArgumentException("Invalid SKU format: " + sku);
        }

        return sku.substring(0, lastHyphenIndex);
    }

}
