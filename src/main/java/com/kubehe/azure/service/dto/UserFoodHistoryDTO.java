package com.kubehe.azure.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
public class UserFoodHistoryDTO {
  private String name;
  private String food;
  private Long calories;
  private Timestamp dateOfConsumption;
}
