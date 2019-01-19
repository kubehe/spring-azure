package com.kubehe.azure.service;


import com.kubehe.azure.domain.FoodEntity;
import com.kubehe.azure.domain.UserEntity;
import com.kubehe.azure.repository.FoodRepository;
import com.kubehe.azure.repository.UserFoodHistoryRepository;
import com.kubehe.azure.repository.UserRepository;
import com.kubehe.azure.service.dto.UserFoodHistoryDTO;
import com.kubehe.azure.service.dto.UserFoodHistoryRequest;
import com.kubehe.azure.service.mapper.UserFoodHistoryMapper;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserFoodHistoryService {

  private UserFoodHistoryRepository userFoodHistoryRepository;
  private FoodRepository foodRepository;
  private UserRepository userRepository;

  @Autowired
  public UserFoodHistoryService(UserFoodHistoryRepository userFoodHistoryRepository, UserRepository userRepository, FoodRepository foodRepository) {
    this.userFoodHistoryRepository = userFoodHistoryRepository;
    this.userRepository = userRepository;
    this.foodRepository = foodRepository;
  }

  public UserFoodHistoryDTO addFoodHistory(UserFoodHistoryRequest userFoodHistoryRequest) {
    var user = getUserEntity(userFoodHistoryRequest.getName());
    var food = getFoodEntity(userFoodHistoryRequest.getFood());

    var userFoodHistoryEntity = userFoodHistoryRepository.save(UserFoodHistoryMapper.MAPPER.toEntity(userFoodHistoryRequest, user, food));

    user.addUserFoodHistoryEntity(userFoodHistoryEntity);
    food.addUserFoodHistoryEntity(userFoodHistoryEntity);

    userRepository.save(user);
    foodRepository.save(food);

    return UserFoodHistoryMapper.MAPPER.toDTO(userFoodHistoryEntity);
  }

  public UserFoodHistoryDTO removeFoodHistory(UserFoodHistoryRequest userFoodHistoryRequest) {
    var user = getUserEntity(userFoodHistoryRequest.getName());
    var food = getFoodEntity(userFoodHistoryRequest.getFood());

    var usersFoodHistory = Optional.of(user.getUserFoodHistory())
      .orElseThrow(() -> new EntityNotFoundException("LOL such food does not exist"));

    var userFoodHistoryEntity = usersFoodHistory.stream()
      .filter(userFoodHistory -> userFoodHistory.getDateOfConsumption().equals(userFoodHistoryRequest.getDateOfConsumption()))
      .findFirst()
      .orElseThrow(() -> new EntityNotFoundException("Entity with such date: " + userFoodHistoryRequest.getDateOfConsumption() + " does not exist"));
    user.getUserFoodHistory().remove(userFoodHistoryEntity);
    food.getUserFoodHistory().remove(userFoodHistoryEntity);
    userRepository.save(user);
    foodRepository.save(food);
    userFoodHistoryRepository.delete(userFoodHistoryEntity);
    return UserFoodHistoryMapper.MAPPER.toDTO(userFoodHistoryEntity);
  }


  private UserEntity getUserEntity(String name) {
    return Optional.of(userRepository.findByName(name)).orElseThrow(() -> new EntityNotFoundException("User with mail: " + name + " not found"));
  }

  private FoodEntity getFoodEntity(String foodName) {
    return Optional.of(foodRepository.findFoodEntityByName(foodName)).orElseThrow(() -> new EntityNotFoundException("Food with name: " + foodName + " not found"));
  }

}
