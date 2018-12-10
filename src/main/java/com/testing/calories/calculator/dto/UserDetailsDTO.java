package com.testing.calories.calculator.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class UserDetailsDTO {
  @Email
  @NotNull
  private String email;
  @NotNull
  @NotEmpty
  private String sex;
  @NotNull
  @NotEmpty
  private Integer height;
  @NotNull
  @NotEmpty
  private Integer weight;
  @NotNull
  @NotEmpty
  private Integer age;
  @NotNull
  @NotEmpty
  private String physicalActivity;
}
