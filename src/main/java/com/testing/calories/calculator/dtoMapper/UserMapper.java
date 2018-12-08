package com.testing.calories.calculator.dtoMapper;

import com.testing.calories.calculator.dto.UserDTO;
import com.testing.calories.calculator.model.UserEntity;

import java.util.stream.Collectors;

public class UserMapper {
  static public UserDTO toDTO(UserEntity entity) {

    var userHistoryFoodList =
      entity.getUserFoodHistory() == null ?
        null :
        entity.getUserFoodHistory().stream().map(UserFoodHistoryMapper::toDTO).collect(Collectors.toList());

    return UserDTO.builder().email(entity.getEmail()).goal(entity.getGoal()).userFoodHistoryList(userHistoryFoodList).build();
  }

  // todo: add this mapper and test in UserMapperTest
//  static public UserEntity toEntity(UserDTO dto) {
//
//  }
}
