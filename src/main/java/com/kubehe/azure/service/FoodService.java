package com.kubehe.azure.service;


import com.kubehe.azure.domain.FoodEntity;
import com.kubehe.azure.repository.FoodRepository;
import com.kubehe.azure.service.dto.FoodDTO;
import com.kubehe.azure.service.mapper.FoodMapper;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FoodService {

  private FoodRepository foodRepository;

  @Autowired
  public FoodService(FoodRepository foodRepository) {
    this.foodRepository = foodRepository;
  }

  public FoodDTO addFood(FoodDTO foodDTO) {
    var result = foodRepository.save(FoodMapper.MAPPER.toEntity(foodDTO));

    return FoodMapper.MAPPER.toDTO(result);
  }

  public FoodDTO removeFood(String name) {
    var result = getFoodEntity(name);
    foodRepository.delete(result);
    return FoodMapper.MAPPER.toDTO(result);
  }

  public FoodDTO getFood(String name) {
    return FoodMapper.MAPPER.toDTO(getFoodEntity(name));
  }

  public List<FoodDTO> getFoodList() {
    return StreamSupport.stream(foodRepository.findAll().spliterator(), false)
      .map(FoodMapper.MAPPER::toDTO).collect(Collectors.toList());
  }

  private FoodEntity getFoodEntity(String name) {
    return Optional.of(foodRepository.findFoodEntityByName(name))
      .orElseThrow(() ->
        new EntityNotFoundException("Food does not exist with such name: " + name));

  }


}
