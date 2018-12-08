package com.testing.calories.calculator.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDTO {
  private String email;
  private String goal;
  private List<UserFoodHistoryDTO> userFoodHistoryList;
}
