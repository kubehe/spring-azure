package com.kubehe.azure.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@NoArgsConstructor
@Data
public class UserFoodHistoryRequest {
  @NotNull
  String name;
  @NotNull
  @NotEmpty
  String food;
  @NotNull
  Timestamp dateOfConsumption;
}
