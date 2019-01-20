package com.kubehe.azure.service.mapper;

import com.kubehe.azure.domain.FoodEntity;
import com.kubehe.azure.domain.UserFoodHistoryEntity;
import com.kubehe.azure.service.dto.FoodDTO;
import com.kubehe.azure.service.dto.FoodRequest;
import com.kubehe.azure.service.dto.UserFoodHistoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface FoodMapper {
  FoodMapper MAPPER = Mappers.getMapper(FoodMapper.class);

  @Mapping(target = "userFoodHistory", qualifiedByName = "toDTO")
  FoodDTO toDTO(FoodEntity foodEntity);

  FoodEntity toEntity(FoodRequest foodRequest);

  @Named("toDTO")
  default Set<UserFoodHistoryDTO> mapUserFoodHistoryEntity(Set<UserFoodHistoryEntity> userFoodHistory) {
    return userFoodHistory.stream().map(UserFoodHistoryMapper.MAPPER::toDTO).collect(Collectors.toSet());
  }

}
