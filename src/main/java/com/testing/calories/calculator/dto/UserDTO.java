package com.testing.calories.calculator.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import java.util.List;

@Data
@Builder
public class UserDTO {
  @Email
  private String email;
  private String goal;
  private List<UserFoodHistoryDTO> userFoodHistoryList;
}
