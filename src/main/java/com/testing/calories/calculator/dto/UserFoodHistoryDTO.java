package com.testing.calories.calculator.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class UserFoodHistoryDTO {
  private String email;
  private String food;
  private Long calories;
  private Date dateOfConsumption;
}
