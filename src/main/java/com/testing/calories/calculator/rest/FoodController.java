package com.testing.calories.calculator.rest;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/food")
public class FoodController {

//  @Autowired
//  private FoodService foodService;
//
//  @GetMapping(path = "/{id}")
//  @ResponseStatus(HttpStatus.OK)
//  public ResponseEntity<FoodDTO> getFood(@PathVariable("id") Long id) {
//    var result = new FoodDTO();
//    return new ResponseEntity<>(result, HttpStatus.OK);
//  }
//
//  @GetMapping
//  @ResponseStatus(HttpStatus.OK)
//  public ResponseEntity<List<FoodDTO>> getFoodList() {
//    var result = new ArrayList<FoodDTO>();
//    return new ResponseEntity<>(result, HttpStatus.OK);
//  }
//
//  @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
//  @ResponseStatus(HttpStatus.CREATED)
//  public ResponseEntity<FoodDTO> addFood() {
//    var result = new FoodDTO();
//    return new ResponseEntity<>(result, HttpStatus.CREATED);
//  }
//
//  @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
//  @ResponseStatus(HttpStatus.OK)
//  public ResponseEntity<FoodDTO> removeFood(@PathVariable("id") Long id) {
//    var result = new FoodDTO();
//    return new ResponseEntity<>(result, HttpStatus.OK);
//  }
//
//  @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
//  @ResponseStatus(HttpStatus.OK)
//  public ResponseEntity<FoodDTO> updateFood(@PathVariable("id") Long id) {
//    var result = new FoodDTO();
//    return new ResponseEntity<>(result, HttpStatus.OK);
//  }
}
