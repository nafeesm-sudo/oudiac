package com.app.oudiac.repositories;

import com.app.oudiac.models.Product;
import com.app.oudiac.models.ProductVariant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant,Long> {
    @Override
    Page<ProductVariant> findAll(Pageable pageable);
}
