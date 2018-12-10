package com.testing.calories.calculator.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class UserDTO {
  @NotNull
  @Email
  private String email;
  @NotNull
  @NotEmpty
  private String goal;

  private List<UserFoodHistoryDTO> userFoodHistoryList;
}
