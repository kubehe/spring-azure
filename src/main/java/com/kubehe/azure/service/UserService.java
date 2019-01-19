package com.kubehe.azure.service;

import com.kubehe.azure.domain.UserEntity;
import com.kubehe.azure.repository.UserRepository;
import com.kubehe.azure.service.dto.UserDTO;
import com.kubehe.azure.service.dto.UserRegisterRequest;
import com.kubehe.azure.service.mapper.UserMapper;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

  private UserRepository userRepository;

  public UserDTO getUser(String email) {
    return UserMapper.MAPPER.toDTO(getUserEntity(email));
  }

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserDTO addUser(UserRegisterRequest userRegisterRequest) {
    var result = userRepository.save(UserMapper.MAPPER.toEntity(userRegisterRequest));
    return UserMapper.MAPPER.toDTO(result);
  }

  public UserDTO removeUser(String name) {
    var user = getUserEntity(name);
    userRepository.delete(user);
    return UserMapper.MAPPER.toDTO(user);
  }

  public List<UserDTO> getUserList() {
    return StreamSupport.stream(userRepository.findAll().spliterator(), false)
      .map(UserMapper.MAPPER::toDTO)
      .collect(Collectors.toList());
  }

  private UserEntity getUserEntity(String name) {
    return Optional.of(userRepository.findByName(name))
      .orElseThrow(
        () -> new EntityNotFoundException("User does not exist with such name: " + name));
  }
}
