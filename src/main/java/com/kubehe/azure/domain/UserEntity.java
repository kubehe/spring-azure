package com.kubehe.azure.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "password")
@Entity
public class UserEntity {

  public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

  private @Id
  @GeneratedValue
  Long id;

  private String name;

  private @JsonIgnore
  String password;

  private String[] roles;

  public void setPassword(String password) {
    this.password = PASSWORD_ENCODER.encode(password);
  }

  public static class UserEntityBuilder {
    public UserEntityBuilder password(String password) {
      this.password = PASSWORD_ENCODER.encode(password);
      return this;
    }
  }
}
