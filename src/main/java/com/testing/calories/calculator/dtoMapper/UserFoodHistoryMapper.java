package com.testing.calories.calculator.dtoMapper;

import com.testing.calories.calculator.dto.UserFoodHistoryDTO;
import com.testing.calories.calculator.model.UserFoodHistoryEntity;

public class UserFoodHistoryMapper {
  static public UserFoodHistoryDTO toDTO(UserFoodHistoryEntity entity) {
    return UserFoodHistoryDTO.builder()
      .calories(entity.getFood() == null ? null : entity.getFood().getCalories())
      .dateOfConsumption(entity.getDateOfConsumption())
      .email(entity.getUser() == null ? "anonymous user" : entity.getUser().getEmail())
      .food(entity.getFood() == null ? null : entity.getFood().getName())
      .build();
  }

  static public UserFoodHistoryEntity toEntity(UserFoodHistoryDTO dto) {
    return UserFoodHistoryEntity.builder().dateOfConsumption(dto.getDateOfConsumption()).build();
  }
}
