package com.iktpreobuka.project.repositories;

import com.iktpreobuka.project.entities.CategoryEntity;
import com.iktpreobuka.project.entities.OfferEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OfferRepository extends CrudRepository<OfferEntity, Integer> {
    List<OfferEntity> findAllByActionPriceBetween(Double lowerPrice, Double upperPrice);

    List<OfferEntity> findAllByCategory(CategoryEntity category);
}
