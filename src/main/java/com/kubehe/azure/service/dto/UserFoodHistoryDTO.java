package com.kubehe.azure.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@Data
public class UserFoodHistoryDTO {
  private String name;
  private String food;
  private Long calories;
  private Date dateOfConsumption;
}
