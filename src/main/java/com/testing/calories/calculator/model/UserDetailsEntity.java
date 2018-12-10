package com.testing.calories.calculator.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(exclude = {"user"})
@Entity
public class UserDetailsEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String sex;

  private Integer height;

  private Integer weight;

  private Integer age;

  private String physicalActivity;

  @OneToOne
  private UserEntity user;
}
