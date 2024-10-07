package com.CategoryProductCurd.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.CategoryProductCurd.entity.Product;
import com.CategoryProductCurd.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	 @Autowired
	    private ProductService productService;

	  @GetMapping
	    public ResponseEntity<Page<Product>> getAllProducts(Pageable pageable) {
	        Page<Product> products = productService.getAllProducts(pageable);
	        return new ResponseEntity<>(products, HttpStatus.OK);
	    }

	    @PostMapping
	    public ResponseEntity<String> createProduct(@RequestBody Product product) {
	        Product createdProduct = productService.createProduct(product);
	        return new ResponseEntity<>("Product created successfully with ID: " + createdProduct.getId(), HttpStatus.CREATED);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Object> getProductById(@PathVariable Long id) {
	        System.out.println("Fetching product with ID: " + id);
	        Optional<Product> product = productService.getProductById(id);
	        if (product.isPresent()) {
	            System.out.println("Product found: " + product.get());
	            return new ResponseEntity<>(product.get(), HttpStatus.OK);
	        } else {
	            System.out.println("Product not found");
	            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
	        }
	    }


	    @PutMapping("/{id}")
	    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product product) {
	        if (productService.getProductById(id).isPresent()) {
	            productService.updateProduct(id, product);
	            return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
	        }
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
	        if (productService.getProductById(id).isPresent()) {
	            productService.deleteProduct(id);
	            return new ResponseEntity<>("Product deleted successfully", HttpStatus.NO_CONTENT);
	        } else {
	            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
	        }
	    }
}
