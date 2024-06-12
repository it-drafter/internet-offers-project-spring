package com.iktpreobuka.project.services;

import com.iktpreobuka.project.entities.CategoryEntity;
import com.iktpreobuka.project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryDaoImpl implements CategoryDao {
    @Autowired
    protected CategoryRepository categoryRepository;


    @Override
    public Iterable<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryEntity addNewCategory(CategoryEntity newCategory) {
        categoryRepository.save(newCategory);

        return newCategory;
    }

    @Override
    public CategoryEntity modifyExistingCategory(String id, CategoryEntity changedCategory) {
        CategoryEntity categoryDb = categoryRepository.findById(Integer.parseInt(id)).orElse(null);

        if (categoryDb != null) {
            if (categoryDb.getId().equals(Integer.parseInt(id))) {
                if (changedCategory.getCategoryName() != null) {
                    categoryDb.setCategoryName(changedCategory.getCategoryName());
                }

                if (changedCategory.getCategoryDescription() != null) {
                    categoryDb.setCategoryDescription(changedCategory.getCategoryDescription());
                }

                categoryRepository.save(categoryDb);

                return categoryDb;
            }
        }

        return null;
    }

    @Override
    public CategoryEntity deleteCategoryById(String id) {
        CategoryEntity categoryDb = categoryRepository.findById(Integer.parseInt(id)).orElse(null);

        if (categoryDb != null) {
//            if (categoryDb.getId().equals(Integer.parseInt(id))) {
            categoryRepository.delete(categoryDb);

            return categoryDb;
//            }
        }

        return null;
    }

    @Override
    public CategoryEntity getCategoryById(String id) {
        return categoryRepository.findById(Integer.parseInt(id)).orElse(null);
    }
}
