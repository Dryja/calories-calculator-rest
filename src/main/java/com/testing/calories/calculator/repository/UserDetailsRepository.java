package com.testing.calories.calculator.repository;

import com.testing.calories.calculator.model.UserDetailsEntity;
import com.testing.calories.calculator.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends CrudRepository<UserDetailsEntity, Long> {
  UserDetailsEntity findUserDetailsEntityByUser(UserEntity user);
}
