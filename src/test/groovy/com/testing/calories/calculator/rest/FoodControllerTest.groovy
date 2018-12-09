package com.testing.calories.calculator.rest

import com.testing.calories.calculator.dto.FoodDTO
import com.testing.calories.calculator.service.FoodService
import org.springframework.beans.factory.annotation.Autowired
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

@WebMvcTest(controllers = FoodController.class)
class FoodControllerTest extends Specification {

    @Autowired
    MockMvc mvc

    @Autowired
    FoodService foodService

    def "GetFood"() {
        given:
        foodService.getFood("python") >> FoodDTO.builder().name("python").calories(100L).userFoodHistoryList([]).build()

        expect: "controller returns UserDTO"
        mvc.perform(get("/food/{name}", "python"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                "{\n" +
                        "  \"name\": \"python\",\n" +
                        "  \"calories\": 100,\n" +
                        "  \"userFoodHistoryList\": []\n" +
                        "}"))
    }

    def "GetFoodList"() {
        given:
        foodService.getFoodList() >> [
                FoodDTO.builder().name("python").calories(100L).userFoodHistoryList([]).build(),
                FoodDTO.builder().name("java").calories(99999999L).userFoodHistoryList([]).build()
        ]

        expect: "controller returns list of FoodDTO properly"
        mvc.perform(get("/food"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                "[\n" +
                        "  {\n" +
                        "    \"name\": \"python\",\n" +
                        "    \"calories\": 100,\n" +
                        "    \"userFoodHistoryList\": []\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"name\": \"java\",\n" +
                        "    \"calories\": 99999999,\n" +
                        "    \"userFoodHistoryList\": []\n" +
                        "  }\n" +
                        "]"
        ))
    }

    def "AddFood"() {
        given:
        foodService.addFood(FoodDTO.builder()
                .name("python")
                .calories(100L).build()) >>
                FoodDTO.builder()
                        .name("python")
                        .calories(100L).build()

        expect: "controller returns newly created FoodDTO properly"
        mvc.perform(post("/food").contentType(MediaType.APPLICATION_JSON).content(
                "{\n" +
                        "  \"calories\": 100,\n" +
                        "  \"name\": \"python\"\n" +
                        "}"
        ))
                .andExpect(status().isCreated())
                .andExpect(content().json(
                "{\n" +
                        "  \"name\": \"python\",\n" +
                        "  \"calories\": 100,\n" +
                        "  \"userFoodHistoryList\": null\n" +
                        "}"
        ))
    }

    def "RemoveFood"() {
    }

    def "UpdateFood"() {
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
