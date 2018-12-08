package com.testing.calories.calculator.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Builder
public class UserFoodHistoryEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private UserEntity user;

  @ManyToOne
  private FoodEntity food;

  private Date dateOfConsumption;
}
