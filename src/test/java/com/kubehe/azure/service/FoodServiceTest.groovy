package com.kubehe.azure.service

import com.kubehe.azure.domain.FoodEntity
import com.kubehe.azure.repository.FoodRepository
import com.kubehe.azure.service.dto.FoodRequest
import spock.lang.Specification

class FoodServiceTest extends Specification {

    FoodService foodService

    FoodRepository foodRepository = Mock()

    def setup() {
        foodService = new FoodService(foodRepository)
    }

    def "AddFood"() {
        given:
        def foodRequest = new FoodRequest()
        foodRequest.setName(name)
        foodRequest.setCalories(calories)

        def foodEntity = FoodEntity.builder().name(name).calories(calories).build()

        and:
        foodRepository.findFoodEntityByName(name) >> foodEntity
        foodRepository.save(foodEntity) >> foodEntity

        when:
        def result = foodService.addFood(foodRequest)

        then:
        assert result.calories == calories
        assert result.name == name

        where:
        name         | calories
        "orange"     | 50L
        "watermelon" | 150L
    }

    def "RemoveFood"() {
        given:
        def foodEntity = FoodEntity.builder().calories(calories).name(name).build()

        and:
        foodRepository.findFoodEntityByName(name) >> foodEntity

        and:
        foodRepository.delete(foodEntity) >> {}

        when:
        def result = foodService.removeFood(name)

        then:
        assert result.calories == calories
        assert result.name == name

        where:
        name         | calories
        "orange"     | 50L
        "watermelon" | 150L
    }

    def "GetFood"() {
        given:
        def foodEntity = FoodEntity.builder().calories(calories).name(name).build()

        and:
        foodRepository.findFoodEntityByName(name) >> foodEntity

        when:
        def result = foodService.getFood(name)

        then:
        assert result.name == name
        assert result.calories == calories

        where:
        name         | calories
        "orange"     | 50L
        "watermelon" | 150L
    }

    def "GetFoodList"() {
        given:
        def foodList = [
                FoodEntity.builder().name("test").calories(50L).build(),
                FoodEntity.builder().name("test1").calories(50L).build(),
                FoodEntity.builder().name("test2").calories(50L).build(),
                FoodEntity.builder().name("test3").calories(50L).build(),
                FoodEntity.builder().name("test4").calories(50L).build(),
        ]

        and:
        foodRepository.findAll() >> foodList

        when:
        def result = foodService.getFoodList()

        then:
        assert result.size() == foodList.size()
        assert result.get(0).name == foodList.get(0).name

    }
}
