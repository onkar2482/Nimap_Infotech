package com.CategoryProductCurd.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CategoryProductCurd.entity.Category;
import com.CategoryProductCurd.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	@Autowired
    private CategoryService categoryService;

	 @GetMapping
	    public ResponseEntity<Page<Category>> getAllCategories(Pageable pageable) {
	        Page<Category> categories = categoryService.getAllCategories(pageable);
	        return new ResponseEntity<>(categories, HttpStatus.OK);
	    }

	    @PostMapping
	    public ResponseEntity<String> createCategory(@RequestBody Category category) {
	        Category createdCategory = categoryService.createCategory(category);
	        return new ResponseEntity<>("Category created successfully with ID: " + createdCategory.getId(), HttpStatus.CREATED);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Object> getCategoryById(@PathVariable Long id) {
	        Optional<Category> category = categoryService.getCategoryById(id);
	        if (category.isPresent()) {
	            return new ResponseEntity<>(category.get(), HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
	        }
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<String> updateCategory(@PathVariable Long id, @RequestBody Category category) {
	        if (categoryService.getCategoryById(id).isPresent()) {
	            categoryService.updateCategory(id, category);
	            return new ResponseEntity<>("Category updated successfully", HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
	        }
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
	        if (categoryService.getCategoryById(id) != null) {
	            categoryService.deleteCategory(id);
	            return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
	        }
	    }
   
}
