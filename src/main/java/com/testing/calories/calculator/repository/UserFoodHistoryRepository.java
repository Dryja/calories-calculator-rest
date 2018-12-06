package com.testing.calories.calculator.repository;

import com.testing.calories.calculator.model.UserFoodHistoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFoodHistoryRepository extends CrudRepository<UserFoodHistoryEntity, Long> {
}
