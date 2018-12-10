package com.testing.calories.calculator.rest;


import com.testing.calories.calculator.dto.UserDTO;
import com.testing.calories.calculator.dto.UserDetailsDTO;
import com.testing.calories.calculator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/details")
public class UserDetailsController {

    private UserService userService;

    @Autowired
    public UserDetailsController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{email}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDetailsDTO> getUser(@PathVariable("email") String email) {
        var result = userService.getUserDetails(email);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//  @PutMapping(path = "/{email}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
//  @ResponseStatus(HttpStatus.OK)
//  public ResponseEntity<UserDTO> updateUser(@PathVariable("email") String email) {
//    var result =
//    return new ResponseEntity<>(result, HttpStatus.OK);
//  }
}

