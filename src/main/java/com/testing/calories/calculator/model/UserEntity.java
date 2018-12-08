package com.testing.calories.calculator.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@EqualsAndHashCode(exclude = {"userFoodHistory"})
@Entity
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;

  private String goal;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  private Set<UserFoodHistoryEntity> userFoodHistory;

  public void addUserFoodHistoryEntity(UserFoodHistoryEntity entity) {
    entity.setUser(this);
    if (this.userFoodHistory == null) {
      this.userFoodHistory = new HashSet<>();
    }
    this.userFoodHistory.add(entity);
  }
}
