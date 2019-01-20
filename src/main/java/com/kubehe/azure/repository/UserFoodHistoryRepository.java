package com.kubehe.azure.repository;

import com.kubehe.azure.domain.UserFoodHistoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFoodHistoryRepository extends CrudRepository<UserFoodHistoryEntity, Long> {
}
