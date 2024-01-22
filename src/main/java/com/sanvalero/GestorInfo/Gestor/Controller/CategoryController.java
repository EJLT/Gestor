package com.sanvalero.GestorInfo.Gestor.Controller;

import com.sanvalero.GestorInfo.Gestor.Service.CategoryService;
import com.sanvalero.GestorInfo.Gestor.domain.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getFilteredCategories(
            @RequestParam(required = false) String name
    ) {
        logger.debug("Fetching categories with name: {}", name);
        List<Category> filteredCategories = categoryService.getFilteredCategories(name);
        return new ResponseEntity<>(filteredCategories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        logger.debug("Fetching category with ID: {}", id);
        Optional<Category> category = categoryService.getCategoryById(id);
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        logger.debug("Creating a new category: {}", category);
        Category createdCategory = categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        logger.debug("Updating category with ID: {}", id);
        Category updatedCategory = categoryService.updateCategory(id, category);
        if (updatedCategory != null) {
            return ResponseEntity.ok(updatedCategory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        logger.debug("Deleting category with ID: {}", id);
        categoryService.deleteCategory(id);
        String message = "Categoría con ID " + id + " se ha borrado correctamente.";
        return ResponseEntity.ok(message);
    }

}
