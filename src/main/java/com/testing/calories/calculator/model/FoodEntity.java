package com.testing.calories.calculator.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(exclude = {"userFoodHistory"})
@Entity
public class FoodEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String name;

  private Long calories;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "food")
  private Set<UserFoodHistoryEntity> userFoodHistory;

  public void addUserFoodHistoryEntity(UserFoodHistoryEntity entity) {
    entity.setFood(this);
    if (this.userFoodHistory == null) {
      this.userFoodHistory = new HashSet<>();
    }
    this.userFoodHistory.add(entity);
  }
}
