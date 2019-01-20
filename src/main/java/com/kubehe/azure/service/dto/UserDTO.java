package com.kubehe.azure.service.dto;


import com.kubehe.azure.authorization.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

@NoArgsConstructor
@Data
public class UserDTO {
  @NotNull
  private String name;
  private UserRole[] roles;
  private Set<UserFoodHistoryDTO> userFoodHistory;
}
