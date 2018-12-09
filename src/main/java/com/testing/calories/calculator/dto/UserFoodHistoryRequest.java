package com.testing.calories.calculator.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import java.sql.Date;

@Data
public class UserFoodHistoryRequest {
  @Email
  String email;
  String foodName;
  Date dateOfConsumption;
}
