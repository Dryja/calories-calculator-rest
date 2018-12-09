package com.testing.calories.calculator.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
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
  @PastOrPresent
  Date dateOfConsumption;
}
