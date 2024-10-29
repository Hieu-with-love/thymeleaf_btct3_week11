package com.ex3.Exltweb.controller;

import com.ex3.Exltweb.dto.CategoryDTO;
import com.ex3.Exltweb.entity.Category;
import com.ex3.Exltweb.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/categories")
    public String showCategories(Model model){
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "categories";
    }

    @GetMapping("/add-category")
    public String showAddCategory(Model model){
        CategoryDTO newCategoryDTO = new CategoryDTO();
        model.addAttribute("categoryDTO", newCategoryDTO);
        return "add-category";
    }

    @PostMapping("/add-category")
    public String addCategory(@RequestParam CategoryDTO categoryDTO, Model model){

        return "add-category";
    }

    @PostMapping("/update-category")
    public String updateCategory(Model model){

        return "update-category";
    }

    @GetMapping("/delete-category")
    public String deleteCategory(Model model){

        return "redirect:/categories";
    }
}
