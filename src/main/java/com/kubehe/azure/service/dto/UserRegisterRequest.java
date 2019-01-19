package com.kubehe.azure.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class UserRegisterRequest {
  @NotNull
  private String name;
  private String[] roles;
  @NotNull
  private String password;
}
