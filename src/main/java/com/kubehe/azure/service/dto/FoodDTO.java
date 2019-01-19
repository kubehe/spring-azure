package com.kubehe.azure.service.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

@NoArgsConstructor
@Data
public class FoodDTO {
  @NotNull
  private String name;
  @NotNull
  private Long calories;
  private Set<UserFoodHistoryDTO> userFoodHistoryList;
}
