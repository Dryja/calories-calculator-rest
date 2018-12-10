package com.testing.calories.calculator.dtoMapper;

import com.testing.calories.calculator.dto.UserDTO;
import com.testing.calories.calculator.dto.UserDetailsDTO;
import com.testing.calories.calculator.model.UserEntity;

import java.util.stream.Collectors;

public class UserDetailsMapper {
    static public UserDetailsDTO toDTO(UserEntity entity) {

        var userHistoryFoodList =
                entity.getUserFoodHistory() == null ?
                        null :
                        entity.getUserFoodHistory().stream().map(UserFoodHistoryMapper::toDTO).collect(Collectors.toList());

        return UserDetailsDTO.builder().email(entity.getEmail()).goal(entity.getGoal()).height(entity.getHeight())
                .weight(entity.getWeight()).about(entity.getAbout()).userFoodHistoryList(userHistoryFoodList).build();
    }

    static public UserEntity toEntity(UserDetailsDTO dto) {
        return UserEntity.builder().email(dto.getEmail()).goal(dto.getGoal()).height(dto.getHeight())
                .weight(dto.getWeight()).about(dto.getAbout()).build();

    }
}
