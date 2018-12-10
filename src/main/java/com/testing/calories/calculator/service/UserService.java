package com.testing.calories.calculator.service;

import com.testing.calories.calculator.dto.UserDTO;
import com.testing.calories.calculator.dto.UserDetailsDTO;
import com.testing.calories.calculator.dtoMapper.UserDetailsMapper;
import com.testing.calories.calculator.dtoMapper.UserMapper;
import com.testing.calories.calculator.model.UserEntity;
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

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
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

  private UserEntity getUserEntity(String email) {
    return Optional.of(userRepository.findUserEntityByEmail(email))
      .orElseThrow(() ->
        new EntityNotFoundException("User does not exist with such email: " + email));

  }

  public UserDetailsDTO getUserDetails(String email) {
    return UserDetailsMapper.toDTO(getUserEntity(email));
  }


}
