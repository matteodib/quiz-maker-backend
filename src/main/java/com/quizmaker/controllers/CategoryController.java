package com.quizmaker.controllers;

import com.quizmaker.models.Category;
import com.quizmaker.models.dtos.CategoryDTO;
import com.quizmaker.services.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping(path = "categories")
public class CategoryController {
    @Autowired
    public CategoryService categoryService;
    @GetMapping("/")
    public List<Category> getCategories(){
        return categoryService.getAllCategories();
    }

    @PostMapping("/")
    public Category storeCategory(@RequestBody CategoryDTO request) {
        return this.categoryService.storeCategory(request);
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") Long categoryId) {
        this.categoryService.deleteCategory(categoryId);
    }

}
