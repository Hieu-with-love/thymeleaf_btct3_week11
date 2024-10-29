package com.ex3.Exltweb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ex3.Exltweb.entity.*;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT p FROM Category p WHERE p.name LIKE %?1%")
    Page<Category> findByKeywordContaining(String keyword, Pageable pageable);
}
