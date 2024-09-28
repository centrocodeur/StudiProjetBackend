package com.marien.backend.services.admin.category;

import com.marien.backend.dto.CategoryDto;
import com.marien.backend.entity.Category;

import java.util.List;

public interface CategoryService {
    Category createcategory(CategoryDto categoryDto);

    List<Category> getAllCategories();
}
