package com.kubehe.azure.repository;

import com.kubehe.azure.domain.FoodEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends CrudRepository<FoodEntity, Long> {
  FoodEntity findFoodEntityByName(String name);
}
