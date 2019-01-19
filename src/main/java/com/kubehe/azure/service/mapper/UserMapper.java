package com.kubehe.azure.service.mapper;

import com.kubehe.azure.domain.UserEntity;
import com.kubehe.azure.service.dto.UserDTO;
import com.kubehe.azure.service.dto.UserRegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
  UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

  UserEntity toEntity(UserRegisterRequest userRegisterRequest);

  UserDTO toDTO(UserEntity userEntity);
}
