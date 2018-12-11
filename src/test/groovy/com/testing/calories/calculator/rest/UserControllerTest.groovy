package com.testing.calories.calculator.rest

import com.testing.calories.calculator.dto.UserDTO
import com.testing.calories.calculator.dto.UserDetailsDTO
import com.testing.calories.calculator.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification
import spock.mock.DetachedMockFactory

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [UserController.class, UserDetailsController.class])
class UserControllerTest extends Specification {
    @Autowired
    MockMvc mvc

    @Autowired
    UserService userService


    def "GetUser"() {
        given:
        userService.getUser("test@test.pl") >> UserDTO.builder().email("test@test.pl").goal("test").userFoodHistoryList([]).build()

        expect: "controller returns UserDTO"
        mvc.perform(MockMvcRequestBuilders.get("/user/{email}", "test@test.pl"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                "{\n" +
                        "  \"email\": \"test@test.pl\",\n" +
                        "  \"goal\": \"test\",\n" +
                        "  \"userFoodHistoryList\": []\n" +
                        "}"))
    }

    def "GetUserDetails"() {
        given:
        userService.getUserDetails("test2@test.pl") >> UserDetailsDTO.builder().email("test2@test.pl").goal("test2").weight(120).height(100).userFoodHistoryList([]).build()

        expect: "controller return UserDetailsDTO"
        mvc.perform((MockMvcRequestBuilders.get("/details/{email}", "test2@test.pl")))
                .andExpect(status().isOk())
                .andExpect(content().json(
                "{\n" +
                        "  \"email\": \"test2@test.pl\",\n" +
                        "  \"goal\": \"test2\",\n" +
                        "  \"weight\": 120,\n" +
                        "  \"height\": 100,\n" +
                        "  \"about\": null,\n" +
                        "  \"userFoodHistoryList\": []\n" +

                        "}"
        ))
    }
    def "GetUserList"() {
        given:
        userService.getUserList() >> [
                UserDTO.builder().email("mateusz@podlasie.pl").goal("python").userFoodHistoryList([]).build(),
                UserDTO.builder().email("kuba@mazowsze.pl").goal("javascript").userFoodHistoryList([]).build()
        ]

        expect: "controller returns list of UserDTO properly"
        mvc.perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                "[\n" +
                        "  {\n" +
                        "    \"email\": \"mateusz@podlasie.pl\",\n" +
                        "    \"goal\": \"python\",\n" +
                        "    \"userFoodHistoryList\": []\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"email\": \"kuba@mazowsze.pl\",\n" +
                        "    \"goal\": \"javascript\",\n" +
                        "    \"userFoodHistoryList\": []\n" +
                        "  }\n" +
                        "]"
        ))
    }

    def "AddUser"() {
        given:
        userService.addUser(UserDTO.builder()
                .email("python@python.pl")
                .goal("python niszczy jave").build()) >>
                UserDTO.builder()
                        .email("python@python.pl")
                        .goal("python niszczy jave").build()

        expect: "controller returns newly created UserDTO properly"
        mvc.perform(MockMvcRequestBuilders.post("/user").contentType(MediaType.APPLICATION_JSON).content(
                "{ \"email\":\"python@python.pl\", \"goal\": \"python niszczy jave\"}"
        ))
                .andExpect(status().isCreated())
                .andExpect(content().json(
                "{\n" +
                        "  \"email\": \"python@python.pl\",\n" +
                        "  \"goal\": \"python niszczy jave\",\n" +
                        "  \"userFoodHistoryList\": null\n" +
                        "}"
        ))
    }

    def "RemoveUser"() {
    }

    def "UpdateUser"() {
    }

    @TestConfiguration
    static class MockConfig {
        def detachedMockFactory = new DetachedMockFactory()

        @Bean
        UserService userService() {
            return detachedMockFactory.Stub(UserService)
        }
    }
}
