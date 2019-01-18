package com.kubehe.azure.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

  //  @PreAuthorize("hasRole('Users')")
  @GetMapping("/login")
  public String login() {
    return "logged in";
  }

  @GetMapping("/logout")
  public String logout(HttpSession httpSession) {
    httpSession.invalidate();
    return "logged out";
  }

}
