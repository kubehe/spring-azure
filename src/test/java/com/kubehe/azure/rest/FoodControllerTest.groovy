package com.kubehe.azure.rest

import com.kubehe.azure.authorization.UserAuthorizationService
import com.kubehe.azure.service.FoodService
import com.kubehe.azure.service.dto.FoodDTO
import com.kubehe.azure.service.dto.FoodRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import spock.mock.DetachedMockFactory

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@WebMvcTest(controllers = FoodController.class, secure = false)
class FoodControllerTest extends Specification {
    @Autowired
    MockMvc mvc

    @Autowired
    FoodService foodService

    def "GetFood"() {
        given:
        def foodDto = new FoodDTO()
        foodDto.setCalories(100L)
        foodDto.setName("python")

        and:
        foodService.getFood("python") >> foodDto

        expect: "controller returns UserDTO"
        mvc.perform(get("/api/food/{name}", "python"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                "{\n" +
                        "  \"name\": \"python\",\n" +
                        "  \"calories\": 100,\n" +
                        "  \"userFoodHistory\": null\n" +
                        "}"))
    }

    def "GetFoodList"() {
        given:

        def foodDto0 = new FoodDTO()
        foodDto0.setCalories(100L)
        foodDto0.setName("python")

        def foodDto1 = new FoodDTO()
        foodDto1.setCalories(99999999L)
        foodDto1.setName("java")

        and:
        foodService.getFoodList() >> [
                foodDto0,
                foodDto1
        ]

        expect: "controller returns list of FoodDTO properly"
        mvc.perform(get("/api/food"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                "[\n" +
                        "  {\n" +
                        "    \"name\": \"python\",\n" +
                        "    \"calories\": 100,\n" +
                        "    \"userFoodHistory\": null\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"name\": \"java\",\n" +
                        "    \"calories\": 99999999,\n" +
                        "    \"userFoodHistory\": null\n" +
                        "  }\n" +
                        "]"
        ))
    }

    def "AddFood"() {
        given:
        def foodRequest = new FoodRequest()
        foodRequest.setCalories(100L)
        foodRequest.setName("python")

        def foodDto = new FoodDTO()
        foodDto.setCalories(100L)
        foodDto.setName("python")

        and:
        foodService.addFood(foodRequest) >> foodDto

        expect: "controller returns newly created FoodDTO properly"
        mvc.perform(post("/api/food").contentType(MediaType.APPLICATION_JSON).content(
                "{\n" +
                        "  \"calories\": 100,\n" +
                        "  \"name\": \"python\"\n" +
                        "}"
        ))
                .andExpect(status().isCreated())
                .andExpect(content().json(
                "{\n" +
                        "\"name\": \"python\",\n" +
                        "\"calories\": 100,\n" +
                        "\"userFoodHistory\": null\n" +
                        "}"
        ))
    }

    @TestConfiguration
    static class MockConfig {
        def detachedMockFactory = new DetachedMockFactory()

        @Bean
        FoodService userService() {
            return detachedMockFactory.Stub(FoodService)
        }

    }
}
