package com.ex3.Exltweb.service.impl;

import com.ex3.Exltweb.dto.CategoryDTO;
import com.ex3.Exltweb.entity.Category;
import com.ex3.Exltweb.repository.CategoryRepository;
import com.ex3.Exltweb.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public boolean createCategory(CategoryDTO categoryDTO) {
        String images = "";
        Category category = Category.builder()
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .images(images)
                .build();
        categoryRepository.save(category);
        return true;
    }

    @Override
    public boolean updateCategory(Long id, CategoryDTO categoryDTO) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found Category"));
        // Neu co anh moi, thi xoa anh cu

        // them thuoc tinh moi vao
        existingCategory.setName(categoryDTO.getName());
        existingCategory.setDescription(categoryDTO.getDescription());
        categoryRepository.save(existingCategory);
        return true;
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category getCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found category with id = " + id));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
