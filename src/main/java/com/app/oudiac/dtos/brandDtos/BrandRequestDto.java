package com.app.oudiac.dtos.brandDtos;

import com.app.oudiac.models.Brand;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class BrandRequestDto {
    @NotBlank(message = "Name is required")
    private String name;

    public static Brand fromBrandRequestDtoBrand(BrandRequestDto brandRequestDto) {
        Brand newBrand=new Brand();
        newBrand.setName(brandRequestDto.getName());

        Date date=new Date();
        newBrand.setCreated_at(date);
        newBrand.setUpdated_at(date);
        newBrand.setIsDeleted(false);

        return  newBrand;
    }
}
