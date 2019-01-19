package com.kubehe.azure.service.mapper;

import com.kubehe.azure.domain.FoodEntity;
import com.kubehe.azure.service.dto.FoodDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FoodMapper {
  FoodMapper MAPPER = Mappers.getMapper(FoodMapper.class);

  FoodDTO toDTO(FoodEntity foodEntity);

  FoodEntity toEntity(FoodDTO foodDTO);

}
