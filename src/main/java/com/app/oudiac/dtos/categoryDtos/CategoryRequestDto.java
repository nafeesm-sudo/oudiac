package com.app.oudiac.dtos.categoryDtos;

import com.app.oudiac.models.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class CategoryRequestDto {
    @NotBlank(message = "Category is required")
    private String name;

    public static Category fromCategoryRequestDtoToCategory(CategoryRequestDto category) {
        Category newCategory=new Category();
        newCategory.setName(category.getName());

        Date date=new Date();
        newCategory.setCreated_at(date);
        newCategory.setUpdated_at(date);
        newCategory.setIsDeleted(false);

        return newCategory;
    }
}
