package com.testing.calories.calculator.service;

import com.testing.calories.calculator.dto.FoodDTO;
import com.testing.calories.calculator.dtoMapper.FoodMapper;
import com.testing.calories.calculator.model.FoodEntity;
import com.testing.calories.calculator.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

  private FoodRepository foodRepository;

  @Autowired
  public FoodService(FoodRepository foodRepository) {
    this.foodRepository = foodRepository;
  }

  public FoodDTO addFood(FoodDTO foodDTO) {
    var result = foodRepository.save(FoodMapper.toEntity(foodDTO));

    return FoodMapper.toDTO(result);
  }

  public FoodDTO removeFood(String name) {
    var result = getFoodEntity(name);
    foodRepository.delete(result);
    return FoodMapper.toDTO(result);
  }

  public FoodDTO getFood(String name) {
    return FoodMapper.toDTO(getFoodEntity(name));
  }

  public List<FoodDTO> getFoodList() {
    var foods = new ArrayList<FoodDTO>();

    foodRepository.findAll().forEach(food -> foods.add(FoodMapper.toDTO(food)));
    return foods;

  }

  private FoodEntity getFoodEntity(String name) {
    return Optional.of(foodRepository.findFoodEntityByName(name))
      .orElseThrow(() ->
        new EntityNotFoundException("Food does not exist with such name: " + name));

  }


}
