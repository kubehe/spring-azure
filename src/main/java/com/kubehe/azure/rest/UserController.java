package com.kubehe.azure.rest;

import com.kubehe.azure.service.UserService;
import com.kubehe.azure.service.dto.UserDTO;
import com.kubehe.azure.service.dto.UserRegisterRequest;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping(path = "/{user}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<UserDTO> getUser(@PathVariable("user") String user) {
    var result = userService.getUser(user);
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
  public ResponseEntity<UserDTO> addUser(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
    var result = userService.addUser(userRegisterRequest);
    return new ResponseEntity<>(result, HttpStatus.CREATED);
  }

  @DeleteMapping(path = "/{user}", produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<UserDTO> removeUser(@PathVariable("user") String user) {
    var result = userService.removeUser(user);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @PostMapping(value = "/current")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Map<String, String>> controllerMethod(@AuthenticationPrincipal final Principal user) {
    var userMap = new HashMap<String, String>();
    userMap.put("user", user.getName());
    return new ResponseEntity<>(userMap, HttpStatus.OK);
  }

}
