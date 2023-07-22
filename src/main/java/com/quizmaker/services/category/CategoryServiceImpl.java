package com.quizmaker.services.category;

import com.quizmaker.models.Category;
import com.quizmaker.models.Question;
import com.quizmaker.models.dtos.CategoryDTO;
import com.quizmaker.repositories.CategoryRepository;
import com.quizmaker.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category storeCategory(CategoryDTO request) {
        Category newCategory = new Category();
        newCategory.setName(request.name);
        categoryRepository.save(newCategory);
        return newCategory;
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
