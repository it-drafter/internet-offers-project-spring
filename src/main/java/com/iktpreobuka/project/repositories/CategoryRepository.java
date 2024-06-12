package com.iktpreobuka.project.repositories;

import com.iktpreobuka.project.entities.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer> {
}
