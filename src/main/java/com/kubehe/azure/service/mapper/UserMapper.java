package com.kubehe.azure.service.mapper;

import com.kubehe.azure.domain.UserEntity;
import com.kubehe.azure.domain.UserFoodHistoryEntity;
import com.kubehe.azure.service.dto.UserDTO;
import com.kubehe.azure.service.dto.UserFoodHistoryDTO;
import com.kubehe.azure.service.dto.UserRegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface UserMapper {
  UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

  UserEntity toEntity(UserRegisterRequest userRegisterRequest);

  @Mapping(target = "userFoodHistory", qualifiedByName = "userFoodHistoryMapper")
  UserDTO toDTO(UserEntity userEntity);

  @Named("userFoodHistoryMapper")
  default Set<UserFoodHistoryDTO> mapUserFoodHistoryEntity(Set<UserFoodHistoryEntity> userFoodHistory) {
    if (userFoodHistory == null) return null;
    return userFoodHistory.stream().map(UserFoodHistoryMapper.MAPPER::toDTO).collect(Collectors.toSet());
  }
}
