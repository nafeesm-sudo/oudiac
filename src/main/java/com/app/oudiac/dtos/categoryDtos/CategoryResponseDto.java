package com.app.oudiac.dtos.categoryDtos;

import com.app.oudiac.models.Category;
import lombok.Data;

@Data
public class CategoryResponseDto {
    private Long id;
    private String name;

    public static CategoryResponseDto fromCategoryToCategoryResponseDto(Category category) {
        CategoryResponseDto response=new CategoryResponseDto();
        response.setId(category.getId());
        response.setName(category.getName());
        return response;
    }
}
