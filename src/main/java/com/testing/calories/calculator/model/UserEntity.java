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
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String email;

  private String goal;

  private Integer weight;
  private Integer height;

  private String about;

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
