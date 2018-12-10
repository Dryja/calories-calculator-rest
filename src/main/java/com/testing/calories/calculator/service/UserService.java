package com.testing.calories.calculator.service;

import com.testing.calories.calculator.dto.UserDTO;
import com.testing.calories.calculator.dtoMapper.UserMapper;
import com.testing.calories.calculator.model.UserEntity;
import com.testing.calories.calculator.repository.UserDetailsRepository;
import com.testing.calories.calculator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  private UserRepository userRepository;
  private UserDetailsRepository userDetailsRepository;

  @Autowired
  public UserService(UserRepository userRepository, UserDetailsRepository userDetailsRepository) {
    this.userRepository = userRepository;
    this.userDetailsRepository = userDetailsRepository;
  }

  public UserDTO addUser(UserDTO userDTO) {
    var result = userRepository.save(UserMapper.toEntity(userDTO));
    return UserMapper.toDTO(result);
  }

  public UserDTO removeUser(String email) {
    var user = getUserEntity(email);
    userRepository.delete(user);
    return UserMapper.toDTO(user);
  }

  public List<UserDTO> getUserList() {
    var users = new ArrayList<UserDTO>();

    userRepository.findAll().forEach(user -> users.add(UserMapper.toDTO(user)));
    return users;
  }

  public UserDTO getUser(String email) {
    return UserMapper.toDTO(getUserEntity(email));
  }

  public Integer getEnergyRequirements(String email) {
    var user = getUserEntity(email);
    var goal = user.getGoal();
    var energyRequirements = calculateEnergyRequirements(user);

    switch (goal) {
      case "reduce": {
        energyRequirements *= 0.85;
        break;
      }
      case "gain": {
        energyRequirements *= 1.15;
        break;
      }
      default: {
        break;
      }
    }

    return (int) Math.round(energyRequirements);
  }

  private UserEntity getUserEntity(String email) {
    return Optional.of(userRepository.findUserEntityByEmail(email))
        .orElseThrow(() ->
            new EntityNotFoundException("User does not exist with such email: " + email));

  }

  private Double calculateBMR(UserEntity user) {
    var userDetails = userDetailsRepository.findUserDetailsEntityByUser(user);
    var sex = userDetails.getSex();
    var height = userDetails.getHeight();
    var weight = userDetails.getWeight();
    var age = userDetails.getAge();

    var bmr = 10 * weight + 6.25 * height - 5 * age;
    bmr = sex.equals("male") ? bmr + 5 : bmr - 161;

    return bmr;
  }

  private Double calculateEnergyRequirements(UserEntity user) {
    var userDetails = userDetailsRepository.findUserDetailsEntityByUser(user);
    var physicalActivity = userDetails.getPhysicalActivity();
    var bmr = calculateBMR(user);

    double energyRequirements;

    switch (physicalActivity) {
      case "sedentary": {
        energyRequirements = 1.2 * bmr;
        break;
      }
      case "lightly": {
        energyRequirements = 1.375 * bmr;
        break;
      }
      case "moderately": {
        energyRequirements = 1.55 * bmr;
        break;
      }
      case "very": {
        energyRequirements = 1.725 * bmr;
        break;
      }
      default: {
        energyRequirements = 1.9 * bmr;
        break;
      }
    }

    return energyRequirements;
  }

}
