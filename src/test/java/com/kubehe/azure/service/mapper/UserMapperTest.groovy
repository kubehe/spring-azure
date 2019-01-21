package com.kubehe.azure.service.mapper

import com.kubehe.azure.domain.FoodEntity
import com.kubehe.azure.domain.UserEntity
import com.kubehe.azure.domain.UserFoodHistoryEntity
import spock.lang.Specification

import java.sql.Timestamp

class UserMapperTest extends Specification {
    def "MapUserFoodHistoryEntity"() {
        given: "set of user food history entities"
        def input = Set.of(UserFoodHistoryEntity.builder().dateOfConsumption(new Timestamp(timestamp)).build())

        and: "set of entities mapped to dto"
        def result = UserMapper.MAPPER.mapUserFoodHistoryEntity(input)

        expect:
        assert Integer.toString(result.dateOfConsumption.get(0).hashCode()) == stringTimestamp

        where:
        timestamp  | stringTimestamp
        1547950006 | "1547950006"
        1547863606 | "1547863606"
    }

    def "toDto returns correct entity"() {
        given: "registered user"
        def userEntity = UserEntity.builder().name(name).build()

        and: "added food entity"
        def foodEntity = FoodEntity.builder().name(foodName).build()

        and: "user has eaten food"
        def userFoodHistory = UserFoodHistoryEntity.builder().dateOfConsumption(new Timestamp(timestamp)).user(userEntity).food(foodEntity).build()

        when: "food is added to user"
        userEntity.addUserFoodHistoryEntity(userFoodHistory)

        then: "map userEntity to userDTO"
        def userDTO = UserMapper.MAPPER.toDTO(userEntity)

        expect:
        assert userDTO.name == name
        assert userDTO.userFoodHistory.food.get(0) == foodName
        assert Integer.toString(userDTO.userFoodHistory.dateOfConsumption.get(0).hashCode()) == stringTimestamp

        where:

        name   | foodName | timestamp  | stringTimestamp
        "john" | "apple"  | 1547950006 | "1547950006"
        "mike" | "orange" | 1547863606 | "1547863606"
    }


}
