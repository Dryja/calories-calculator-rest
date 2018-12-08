package com.testing.calories.calculator.repository;

import com.testing.calories.calculator.model.FoodEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends CrudRepository<FoodEntity, Long> {
  FoodEntity findFoodEntityByName(String name);
}
