package com.ex3.Exltweb.service;

import com.ex3.Exltweb.dto.CategoryDTO;
import com.ex3.Exltweb.entity.Category;

import java.util.List;

public interface CategoryService {
    boolean createCategory(CategoryDTO categoryDTO);
    boolean updateCategory(Long id, CategoryDTO categoryDTO);
    void deleteCategory(Long id);
    Category getCategory(Long id);
    List<Category> getAllCategories();
}
