package com.kubehe.azure.service.dto;

import com.kubehe.azure.authorization.UserRole;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class UserRegisterRequest {
  @NotNull
  private String name;
  private UserRole[] roles;
  @NotNull
  private String password;
}
