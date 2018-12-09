package com.testing.calories.calculator.service

import com.testing.calories.calculator.repository.FoodRepository
import com.testing.calories.calculator.repository.UserFoodHistoryRepository
import com.testing.calories.calculator.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@SpringBootTest
@ContextConfiguration
class UserFoodHistoryServiceTest extends Specification {

    @Autowired
    UserFoodHistoryService userFoodHistoryService

    UserRepository userRepository = Mock()
    FoodRepository foodRepository = Mock()
    UserFoodHistoryRepository userFoodHistoryRepository = Mock()

    def "AddFoodHistory"() {

    }

    def "RemoveFoodHistory"() {
    }
}
