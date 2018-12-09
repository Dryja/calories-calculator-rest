package com.testing.calories.calculator.rest;

import com.testing.calories.calculator.dto.UserFoodHistoryDTO;
import com.testing.calories.calculator.dto.UserFoodHistoryRequest;
import com.testing.calories.calculator.service.UserFoodHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/food-diary")
public class UserFoodHistoryController {

  private UserFoodHistoryService userFoodHistoryService;

  @Autowired
  public UserFoodHistoryController(UserFoodHistoryService userFoodHistoryService) {
    this.userFoodHistoryService = userFoodHistoryService;
  }

  @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<UserFoodHistoryDTO> addFoodHistory(@RequestBody UserFoodHistoryRequest userFoodHistoryRequest) {
    var result = userFoodHistoryService.addFoodHistory(
      userFoodHistoryRequest.getEmail(),
      userFoodHistoryRequest.getFoodName(),
      userFoodHistoryRequest.getDateOfConsumption());
    return new ResponseEntity<>(result, HttpStatus.CREATED);
  }

  @DeleteMapping(produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<UserFoodHistoryDTO> removeFoodHistory(@RequestBody UserFoodHistoryRequest userFoodHistoryRequest) {
    var result = userFoodHistoryService.removeFoodHistory(
      userFoodHistoryRequest.getEmail(),
      userFoodHistoryRequest.getFoodName(),
      userFoodHistoryRequest.getDateOfConsumption()
    );
    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
