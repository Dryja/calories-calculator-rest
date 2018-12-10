package com.testing.calories.calculator.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@EqualsAndHashCode(exclude = {"user", "food"})
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
