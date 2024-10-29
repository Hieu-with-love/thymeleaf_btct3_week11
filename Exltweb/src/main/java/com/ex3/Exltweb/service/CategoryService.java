package com.ex3.Exltweb.service;

import com.ex3.Exltweb.dto.CategoryDTO;
import com.ex3.Exltweb.entity.Category;
import org.apache.coyote.BadRequestException;

import java.io.IOException;
import java.util.List;

public interface CategoryService {
    boolean createCategory(CategoryDTO categoryDTO) throws IOException;
    boolean updateCategory(Long id, CategoryDTO categoryDTO);
    void deleteCategory(Long id);
    Category getCategory(Long id);
    List<Category> getAllCategories();
}
