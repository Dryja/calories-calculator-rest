package com.testing.calories.calculator.rest;


import com.testing.calories.calculator.dto.UserDTO;
import com.testing.calories.calculator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/user")
public class UserController {

  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping(path = "/{email}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<UserDTO> getUser(@PathVariable("email") String email) {
    var result = userService.getUser(email);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<UserDTO>> getUserList() {
    var result = userService.getUserList();
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<UserDTO> addUser(@RequestBody @Valid UserDTO userDTO) {
    var result = userService.addUser(userDTO);
    return new ResponseEntity<>(result, HttpStatus.CREATED);
  }

  @DeleteMapping(path = "/{email}", produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<UserDTO> removeUser(@PathVariable("email") String email) {
    var result = userService.removeUser(email);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @GetMapping(path = "/{email}/energy_requirements")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Integer> getEnergyRequirements(@PathVariable("email") String email) {
    var result = userService.getEnergyRequirements(email);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

//  @PutMapping(path = "/{email}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
//  @ResponseStatus(HttpStatus.OK)
//  public ResponseEntity<UserDTO> updateUser(@PathVariable("email") String email) {
//    var result =
//    return new ResponseEntity<>(result, HttpStatus.OK);
//  }
}

