package com.iktpreobuka.project.services;

import com.iktpreobuka.project.entities.CategoryEntity;


public interface CategoryDao {
    public Iterable<CategoryEntity> getAllCategories();

    public CategoryEntity addNewCategory(CategoryEntity newCategory);

    public CategoryEntity modifyExistingCategory(String id, CategoryEntity changedCategory);

    public CategoryEntity deleteCategoryById(String id);

    public CategoryEntity getCategoryById(String id);
}
