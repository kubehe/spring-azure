package com.kubehe.azure.service.dto;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

@NoArgsConstructor
@Data
public class UserDTO {
  @NotNull
  private String name;
  private String[] roles;
  private Set<UserFoodHistoryDTO> userFoodHistoryList;
}
