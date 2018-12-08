package com.testing.calories.calculator.service;

import com.testing.calories.calculator.dto.UserFoodHistoryDTO;
import com.testing.calories.calculator.dtoMapper.UserFoodHistoryMapper;
import com.testing.calories.calculator.model.FoodEntity;
import com.testing.calories.calculator.model.UserEntity;
import com.testing.calories.calculator.model.UserFoodHistoryEntity;
import com.testing.calories.calculator.repository.FoodRepository;
import com.testing.calories.calculator.repository.UserFoodHistoryRepository;
import com.testing.calories.calculator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Date;
import java.util.Optional;

@Service
public class UserFoodHistoryService {

  private UserFoodHistoryRepository userFoodHistoryRepository;
  private FoodRepository foodRepository;
  private UserRepository userRepository;

  @Autowired
  public UserFoodHistoryService(UserFoodHistoryRepository userFoodHistoryRepository, UserRepository userRepository, FoodRepository foodRepository) {
    this.userFoodHistoryRepository = userFoodHistoryRepository;
    this.userRepository = userRepository;
    this.foodRepository = foodRepository;
  }

  public UserFoodHistoryDTO addFoodHistory(String email, String foodName, Date dateOfConsumption) {
    var user = getUserEntity(email);
    var food = getFoodEntity(foodName);

    var userFoodHistoryEntity = userFoodHistoryRepository.save(UserFoodHistoryEntity.builder().dateOfConsumption(dateOfConsumption).build());

    user.addUserFoodHistoryEntity(userFoodHistoryEntity);
    food.addUserFoodHistoryEntity(userFoodHistoryEntity);

    userRepository.save(user);
    foodRepository.save(food);

    return UserFoodHistoryMapper.toDTO(userFoodHistoryEntity);
  }

  public UserFoodHistoryDTO removeFoodHistory(String email, String foodName, Date dateOfConsumption) {
    var user = getUserEntity(email);
    var food = getFoodEntity(foodName);

    var usersFoodHistory = Optional.of(user.getUserFoodHistory())
      .orElseThrow(() -> new EntityNotFoundException("LOL such food does not exist"));

    var userFoodHistoryEntity = usersFoodHistory.stream()
      .filter(userFoodHistory -> userFoodHistory.getDateOfConsumption().equals(dateOfConsumption))
      .findFirst()
      .orElseThrow(() -> new EntityNotFoundException("Entity with such date: " + dateOfConsumption + " does not exist"));
    user.getUserFoodHistory().remove(userFoodHistoryEntity);
    food.getUserFoodHistory().remove(userFoodHistoryEntity);
    userRepository.save(user);
    foodRepository.save(food);
    userFoodHistoryRepository.delete(userFoodHistoryEntity);
    return UserFoodHistoryMapper.toDTO(userFoodHistoryEntity);
  }


  private UserEntity getUserEntity(String email) {
    return Optional.of(userRepository.findUserEntityByEmail(email)).orElseThrow(() -> new EntityNotFoundException("User with mail: " + email + " not found"));
  }

  private FoodEntity getFoodEntity(String foodName) {
    return Optional.of(foodRepository.findFoodEntityByName(foodName)).orElseThrow(() -> new EntityNotFoundException("Food with name: " + foodName + " not found"));
  }

}
