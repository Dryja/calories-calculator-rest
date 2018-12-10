package com.testing.calories.calculator.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
public class UserFoodHistoryRequest {
  @Email
  @NotNull
  String email;
  @NotNull
  @NotEmpty
  String foodName;
  @NotNull
  Date dateOfConsumption;
}
