package com.ex3.Exltweb.service.impl;

import com.ex3.Exltweb.dto.CategoryDTO;
import com.ex3.Exltweb.entity.Category;
import com.ex3.Exltweb.repository.CategoryRepository;
import com.ex3.Exltweb.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public boolean createCategory(CategoryDTO categoryDTO) throws IOException {
        String images = "";
        if (categoryDTO.getImage() == null) {
            images = "/uploads/default-product.jpg";
        } else {
            if (!isValidSuffixImage(Objects.requireNonNull(categoryDTO.getImage().getOriginalFilename()))) {
                throw new BadRequestException("Image is not valid");
            }
            images = storeFile(categoryDTO.getImage());
        }
        Category category = Category.builder()
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .images(images)
                .build();
        categoryRepository.save(category);
        return true;
    }

    // has completed yet
    private boolean isValidSuffixImage(String img) {
        return img.endsWith(".jpg") || img.endsWith(".jpeg") ||
                img.endsWith(".png") || img.endsWith(".gif") ||
                img.endsWith(".bmp");
    }

    private boolean isImage(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image/");
    }

    private String storeFile(MultipartFile file) throws IOException {
        if (file.getSize() == 0)
            return "Anh bi rong";
        if (file.getSize() > 10 * 1024 * 1024) {
            return "File is too large. Maximum size is 10MB";
        }
        if (!isImage(file)) {
            return "File is not an image";
        }
        // get file name
        String fileName = file.getOriginalFilename();
        // generate code random base on UUID
        String uniqueFileName = UUID.randomUUID().toString() + "_" + LocalDate.now() + "_" + fileName;
        java.nio.file.Path uploadDir = Paths.get("uploads");
        // check and create if haven't existed
        if (!Files.exists(uploadDir)) {
            Files.createDirectory(uploadDir);
        }
        java.nio.file.Path destination = Paths.get(uploadDir.toString(), uniqueFileName);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

        return uniqueFileName;
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
