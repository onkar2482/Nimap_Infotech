package com.CategoryProductCurd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CategoryProductCurd.entity.Category;

public interface CategoryRepository  extends JpaRepository<Category, Long>{

}
