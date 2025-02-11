package com.ex3.Exltweb.controller;

import com.ex3.Exltweb.dto.CategoryDTO;
import com.ex3.Exltweb.entity.Category;
import com.ex3.Exltweb.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/categories")
    public String showCategories(Model model,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(required = false) String keyword
    ){
        int size = 3;
        Page<Category> categoryPage;

        if (keyword == null || keyword.isEmpty()) {
            categoryPage = categoryService.getAllCategories(PageRequest.of(page, size));
        } else {
            categoryPage = categoryService.findByKeyword(keyword, PageRequest.of(page, size));
        }

        model.addAttribute("categoryPage", categoryPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", categoryPage.getTotalPages());
        model.addAttribute("keyword", keyword);

        return "categories"; // tên của view template
    }

    @GetMapping("/add-category")
    public String showAddCategory(Model model){
        CategoryDTO newCategoryDTO = new CategoryDTO();
        model.addAttribute("categoryDTO", newCategoryDTO);
        return "add-category";
    }

    @PostMapping("/add-category")
    public String addCategory(@ModelAttribute CategoryDTO categoryDTO, Model model) throws IOException {
        Category category = categoryService.createCategory(categoryDTO);
        if(category != null){
            return "redirect:/categories";
        }
        return "add-category";
    }

    @GetMapping("/update-category")
    public String showUpdateCategory(@RequestParam("id") Long id ,Model model){
        Category category = categoryService.getCategory(id);
        model.addAttribute("category", category);
        return "update-category";
    }

    @PostMapping("/update-category")
    public String updateCategory(@RequestParam("id") Long id,
                                @ModelAttribute CategoryDTO categoryDTO,
                                Model model
    ) throws IOException {
        categoryService.updateCategory(id, categoryDTO);
        Category category = categoryService.getCategory(id);
        model.addAttribute("categoryDTO", categoryDTO);
        model.addAttribute("category", category);
        if(category != null){
            return "redirect:/categories";
        }
        return "update-category";
    }

    @GetMapping("/delete-category")
    public String deleteCategory(@RequestParam("id") Long id,Model model){
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}
