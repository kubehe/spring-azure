package com.kubehe.azure.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
  @Autowired
  @PreAuthorize("hasRole('Users')")
  @RequestMapping("/")
  public String helloWorld() {
    return "Hello World!";
  }
}
