package com.testing.calories.calculator.rest;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/User")
public class UserController {

//  @Autowired
//  private UserService userService;
//
//  @GetMapping(path = "/{id}")
//  @ResponseStatus(HttpStatus.OK)
//  public ResponseEntity<UserDTO> getUser(@PathVariable("id") Long id) {
//    var result = new UserDTO();
//    return new ResponseEntity<>(result, HttpStatus.OK);
//  }
//
//  @GetMapping
//  @ResponseStatus(HttpStatus.OK)
//  public ResponseEntity<List<UserDTO>> getUserList() {
//    var result = new ArrayList<UserDTO>();
//    return new ResponseEntity<>(result, HttpStatus.OK);
//  }
//
//  @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
//  @ResponseStatus(HttpStatus.CREATED)
//  public ResponseEntity<UserDTO> addUser() {
//    var result = new UserDTO();
//    return new ResponseEntity<>(result, HttpStatus.CREATED);
//  }
//
//  @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
//  @ResponseStatus(HttpStatus.OK)
//  public ResponseEntity<UserDTO> removeUser(@PathVariable("id") Long id) {
//    var result = new UserDTO();
//    return new ResponseEntity<>(result, HttpStatus.OK);
//  }
//
//  @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
//  @ResponseStatus(HttpStatus.OK)
//  public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id) {
//    var result = new UserDTO();
//    return new ResponseEntity<>(result, HttpStatus.OK);
//  }
}

