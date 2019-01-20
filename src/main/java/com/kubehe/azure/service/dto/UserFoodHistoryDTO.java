package com.kubehe.azure.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserFoodHistoryDTO {
  private String name;
  private String food;
  private Long calories;
  private String dateOfConsumption;
}
