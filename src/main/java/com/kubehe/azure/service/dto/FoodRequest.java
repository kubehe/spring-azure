package com.kubehe.azure.service.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class FoodRequest {
  @NotNull
  private String name;
  @NotNull
  private Long calories;
}
