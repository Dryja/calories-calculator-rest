package com.testing.calories.calculator.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class FoodDTO {
  @NotNull
  private String name;
  @NotNull
  private Long calories;
  private List<UserFoodHistoryDTO> userFoodHistoryList;
}
