package com.testing.calories.calculator.rest;


import com.testing.calories.calculator.dto.UserDTO;
import com.testing.calories.calculator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/User")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<UserDTO> getUser(@PathVariable("id") Long id) {
    var result = new UserDTO();
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<UserDTO>> getUserList() {
    var result = new ArrayList<UserDTO>();
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<UserDTO> addUser() {
    var result = new UserDTO();
    return new ResponseEntity<>(result, HttpStatus.CREATED);
  }

  @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<UserDTO> removeUser(@PathVariable("id") Long id) {
    var result = new UserDTO();
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id) {
    var result = new UserDTO();
    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}

