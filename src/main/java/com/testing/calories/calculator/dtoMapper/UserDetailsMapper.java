package com.testing.calories.calculator.dtoMapper;

import com.testing.calories.calculator.dto.UserDetailsDTO;
import com.testing.calories.calculator.model.UserDetailsEntity;

public class UserDetailsMapper {
  static public UserDetailsDTO toDTO(UserDetailsEntity entity) {
    return UserDetailsDTO.builder()
        .sex(entity.getSex())
        .height(entity.getHeight())
        .weight(entity.getWeight())
        .age(entity.getAge())
        .physicalActivity(entity.getPhysicalActivity())
        .email(entity.getUser() == null ? "anonymous user" : entity.getUser().getEmail())
        .build();
  }

  static public UserDetailsEntity toEntity(UserDetailsDTO dto) {
    return UserDetailsEntity.builder()
        .sex(dto.getSex())
        .height(dto.getHeight())
        .weight(dto.getWeight())
        .age(dto.getAge())
        .physicalActivity(dto.getPhysicalActivity())
        .build();
  }
}
