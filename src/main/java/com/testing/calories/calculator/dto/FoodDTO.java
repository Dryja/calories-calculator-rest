package com.testing.calories.calculator.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FoodDTO {
  private String name;
  private Long calories;
  private List<UserFoodHistoryDTO> userFoodHistoryList;
}
