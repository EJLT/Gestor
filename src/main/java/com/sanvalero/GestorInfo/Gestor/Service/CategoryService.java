package com.sanvalero.GestorInfo.Gestor.Service;

import com.sanvalero.GestorInfo.Gestor.Repository.CategoryRepository;
import com.sanvalero.GestorInfo.Gestor.domain.Category;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @PersistenceContext
    private EntityManager entityManager;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public List<Category> getFilteredCategories(String name) {
        List<Category> categories = categoryRepository.findCategoriesByFilter(name);

        for (Category category : categories) {
            category.getPublications().size();
        }

        return categories;
    }



    @Transactional
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(category -> {
                    Hibernate.initialize(category.getPublications());
                    return category;
                });
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category updatedCategory) {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if (existingCategory.isPresent()) {
            Category category = existingCategory.get();
            category.setName(updatedCategory.getName());
            return categoryRepository.save(category);
        }
        return null;
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
