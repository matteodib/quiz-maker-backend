package com.quizmaker.services.category;

import com.quizmaker.models.Category;
import com.quizmaker.models.dtos.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    Category storeCategory(CategoryDTO request);

}
