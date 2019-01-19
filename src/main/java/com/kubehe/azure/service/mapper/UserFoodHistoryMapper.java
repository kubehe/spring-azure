package com.kubehe.azure.service.mapper;

import com.kubehe.azure.domain.FoodEntity;
import com.kubehe.azure.domain.UserEntity;
import com.kubehe.azure.domain.UserFoodHistoryEntity;
import com.kubehe.azure.service.dto.UserFoodHistoryDTO;
import com.kubehe.azure.service.dto.UserFoodHistoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserFoodHistoryMapper {

  UserFoodHistoryMapper MAPPER = Mappers.getMapper(UserFoodHistoryMapper.class);

  @Mappings({
    @Mapping(target = "name", source = "userFoodHistoryEntity.user.name"),
    @Mapping(target = "food", source = "userFoodHistoryEntity.food.name"),
    @Mapping(target = "calories", source = "userFoodHistoryEntity.food.calories")
  })
  UserFoodHistoryDTO toDTO(UserFoodHistoryEntity userFoodHistoryEntity);

  @Mappings({
    @Mapping(target = "dateOfConsumption", source = "userFoodHistoryRequest.dateOfConsumption"),
    @Mapping(target = "user", source = "userEntity"),
    @Mapping(target = "food", source = "foodEntity"),
    @Mapping(target = "id", ignore = true)
  })
  UserFoodHistoryEntity toEntity(UserFoodHistoryRequest userFoodHistoryRequest, UserEntity userEntity, FoodEntity foodEntity);
}
