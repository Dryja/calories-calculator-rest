package com.testing.calories.calculator.service;

import com.testing.calories.calculator.repository.UserFoodHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService {

  private UserFoodHistoryRepository userFoodHistoryRepository;

  @Autowired
  public FoodService(UserFoodHistoryRepository userFoodHistoryRepository) {
    this.userFoodHistoryRepository = userFoodHistoryRepository;
  }



}
