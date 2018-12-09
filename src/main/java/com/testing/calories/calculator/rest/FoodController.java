package com.testing.calories.calculator.rest;


import com.testing.calories.calculator.dto.FoodDTO;
import com.testing.calories.calculator.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/food")
public class FoodController {

  private FoodService foodService;

  @Autowired
  public FoodController(FoodService foodService) {
    this.foodService = foodService;
  }

  @GetMapping(path = "/{name}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<FoodDTO> getFood(@PathVariable("name") String name) {
    var result = foodService.getFood(name);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<FoodDTO>> getFoodList() {
    var result = foodService.getFoodList();
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<FoodDTO> addFood(@RequestBody @Valid FoodDTO foodDTO) {
    var result = foodService.addFood(foodDTO);
    return new ResponseEntity<>(result, HttpStatus.CREATED);
  }

  @DeleteMapping(path = "/{name}", produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<FoodDTO> removeFood(@PathVariable("name") String name) {
    var result = foodService.removeFood(name);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

//  @PutMapping(path = "/{name}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE) @ResponseStatus(HttpStatus.OK)
//  public ResponseEntity<FoodDTO> updateFood(@PathVariable("name") String name) {
//    var result = foodService.
//    return new ResponseEntity<>(result, HttpStatus.OK);
//  }
}
