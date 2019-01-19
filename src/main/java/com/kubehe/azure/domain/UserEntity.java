package com.kubehe.azure.domain;

import javax.persistence.*;

import lombok.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "password")
@Entity
public class UserEntity {

  public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

  @Id
  @GeneratedValue
  private Long id;

  @Column(unique = true)
  private String name;

  @JsonIgnore
  private String password;

  private String[] roles;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  private Set<UserFoodHistoryEntity> userFoodHistory;

  public void addUserFoodHistoryEntity(UserFoodHistoryEntity entity) {
    entity.setUser(this);
    if (this.userFoodHistory == null) {
      this.userFoodHistory = new HashSet<>();
    }
    this.userFoodHistory.add(entity);
  }

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
