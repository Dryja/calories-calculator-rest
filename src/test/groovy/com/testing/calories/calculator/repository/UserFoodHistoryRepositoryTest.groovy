package com.testing.calories.calculator.repository

import com.testing.calories.calculator.model.FoodEntity
import com.testing.calories.calculator.model.UserEntity
import com.testing.calories.calculator.model.UserFoodHistoryEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import spock.lang.Specification

import javax.persistence.EntityNotFoundException
import java.sql.Date

@DataJpaTest
class UserFoodHistoryRepositoryTest extends Specification {

    @Autowired
    UserFoodHistoryRepository userFoodHistoryRepository

    @Autowired
    FoodRepository foodRepository

    @Autowired
    UserRepository userRepository

    def "add userFoodHistoryEntity to user and food"() {
        expect:

        def date = new Date(dateOfConsumption[0], dateOfConsumption[1], dateOfConsumption[2]) // xDDD

        def userFoodHistoryEntity = UserFoodHistoryEntity.builder().dateOfConsumption(date).build()
        def userEntity = UserEntity.builder().email(email).goal(goal).build()
        def foodEntity = FoodEntity.builder().name(name).calories(calories).build()

        def userFoodHistory = userFoodHistoryRepository.save(userFoodHistoryEntity)
        userEntity.addUserFoodHistoryEntity(userFoodHistory)
        foodEntity.addUserFoodHistoryEntity(userFoodHistory)

        def user = userRepository.save(userEntity)
        foodRepository.save(foodEntity)

        def foodHistory = userRepository.findById(user.id).orElseThrow { _ -> new EntityNotFoundException("not found") }
                .userFoodHistory.stream()
                .filter { entity -> entity.dateOfConsumption == date }
                .findAny().orElseThrow { _ -> new EntityNotFoundException("userFoodHistory not found") }

        assert userFoodHistoryRepository.existsById(foodHistory.id)

        where:
        email               | goal                   | name      | calories | dateOfConsumption
        "jakub@test.pl"     | "only fat and protein" | "apple"   | 50L      | [2012, 11, 10]
        "szymon@test.pl"    | "go hard or go home"   | "orange"  | 50L      | [2011, 10, 10]
        "hubert@test.pl"    | "python"               | "human"   | 50000L   | [2013, 1, 10]
        "mateusz@test.pl"   | "podlasie"             | "test"    | 50L      | [2015, 5, 10]
        "chaber@test.pl"    | "python4life"          | "boar"    | 5000000L | [2022, 3, 10]
        "jstar@test.pl"     | "magnesy"              | "beer"    | 150L     | [2042, 4, 10]
        "krzysztof@test.pl" | "yerba mate"           | "chicken" | 1150L    | [2052, 11, 10]
    }

}
