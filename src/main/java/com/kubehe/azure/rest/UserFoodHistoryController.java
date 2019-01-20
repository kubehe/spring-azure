package com.kubehe.azure.rest;

import com.kubehe.azure.service.UserFoodHistoryService;
import com.kubehe.azure.service.dto.UserFoodHistoryDTO;
import com.kubehe.azure.service.dto.UserFoodHistoryRequest;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/api/user-food-history")
public class UserFoodHistoryController {

  private UserFoodHistoryService userFoodHistoryService;

  @Autowired
  public UserFoodHistoryController(UserFoodHistoryService userFoodHistoryService) {
    this.userFoodHistoryService = userFoodHistoryService;
  }

  @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<UserFoodHistoryDTO> addFoodHistory(@RequestBody @Valid UserFoodHistoryRequest userFoodHistoryRequest) {
    var result = userFoodHistoryService.addFoodHistory(userFoodHistoryRequest);
    return new ResponseEntity<>(result, HttpStatus.CREATED);
  }

  @DeleteMapping(produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<UserFoodHistoryDTO> removeFoodHistory(@RequestBody @Valid UserFoodHistoryRequest userFoodHistoryRequest) {
    var result = userFoodHistoryService.removeFoodHistory(userFoodHistoryRequest);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
