package com.app.oudiac.services.productService;

import com.app.oudiac.configs.SupabaseConfig.S3Properties;
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
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{

    private final BrandRepository brandRepository;

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    private final ProductVariantRepository productVariantRepository;

    private final S3Client s3Client;
    private final S3Properties properties;

    @Value("${S3_STORAGE_BASE_URL}")
    private String s3StorageBaseUrl;

    @Override
    public ResponseEntity<ProductResponseDto> addProduct(ProductRequestDto requestDto, MultipartFile[] files) throws IOException {

        MultipartFile file=files[0];
        String key = "productImages/"+ System.currentTimeMillis()
                + "-"
                + file.getOriginalFilename();

        PutObjectRequest request =
                PutObjectRequest.builder()
                        .bucket(properties.getBucket())
                        .key(key)
                        .contentType(file.getContentType())
                        .build();

        s3Client.putObject(
                request,
                RequestBody.fromBytes(file.getBytes())
        );

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

        newProduct.setImageUrl(s3StorageBaseUrl+key);
        String code=getProductCode(requestDto.getSku());
        Optional<Product> productOptional=productRepository.findByCode(code);
        if(productOptional.isEmpty()){
            newProduct.setCode(code);
            productRepository.save(newProduct);
        }else {
            newProductVariant.setProduct(productOptional.get());
        }

        productVariantRepository.save(newProductVariant);

        ProductResponseDto responseDto=ProductResponseDto.fromProductToProductResponseDto(newProductVariant);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Override
    public Page<ProductResponseDto> getProducts(Integer page, Integer size) {
        Page<ProductVariant> productPage = productVariantRepository.findAll(PageRequest.of(page, size));
        return productPage.map(ProductResponseDto::fromProductToProductResponseDto);
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
