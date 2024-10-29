package com.ex3.Exltweb.service;

import com.ex3.Exltweb.dto.CategoryDTO;
import com.ex3.Exltweb.entity.Category;
import org.apache.coyote.BadRequestException;

import java.io.IOException;
import java.util.List;

public interface CategoryService {
    Category createCategory(CategoryDTO categoryDTO) throws IOException;
    Category updateCategory(Long id, CategoryDTO categoryDTO) throws IOException;
    void deleteCategory(Long id);
    Category getCategory(Long id);
    List<Category> getAllCategories();
}
