package com.testing.calories.calculator.dtoMapper;

import com.testing.calories.calculator.dto.FoodDTO;
import com.testing.calories.calculator.model.FoodEntity;

import java.util.stream.Collectors;

public class FoodMapper {
  static public FoodDTO toDTO(FoodEntity entity) {

    var userHistoryFoodList =
      entity.getUserFoodHistory() == null ?
        null :
        entity.getUserFoodHistory().stream().map(UserFoodHistoryMapper::toDTO).collect(Collectors.toList());

    return FoodDTO.builder()
      .name(entity.getName())
      .calories(entity.getCalories())
      .userFoodHistoryList(userHistoryFoodList)
      .build();
  }

  // todo: add this mapper and test in FoodMapperTest
//  static public FoodEntity toEntity(FoodDTO dto) {
//
//  }
}
