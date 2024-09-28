package com.marien.backend.controller.admin;


import com.marien.backend.dto.CategoryDto;
import com.marien.backend.entity.Category;
import com.marien.backend.services.admin.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminCategoryController {

    private final CategoryService categoryService;

    @PostMapping("category")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDto categoryDto){

        Category category =categoryService.createcategory(categoryDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(category);

    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

}
