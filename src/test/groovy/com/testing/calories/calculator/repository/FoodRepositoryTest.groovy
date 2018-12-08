package com.testing.calories.calculator.repository

import com.testing.calories.calculator.model.FoodEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import spock.lang.Specification

import javax.persistence.EntityNotFoundException

@DataJpaTest
class FoodRepositoryTest extends Specification {

    @Autowired
    FoodRepository foodRepository

    @Autowired
    UserFoodHistoryRepository userFoodHistoryRepository

    def "test adding food"() {
        expect:
        def foodEntity = foodRepository.save(FoodEntity.builder().name(name).calories(calories).build())
        def food = foodRepository.findById(foodEntity.id).orElseThrow { _ -> new EntityNotFoundException("not found") }
        food.name == name
        food.calories == calories

        where:
        name      | calories
        "apple"   | 50L
        "orange"  | 50L
        "human"   | 50000L
        "test"    | 50L
        "boar"    | 5000000L
        "beer"    | 150L
        "chicken" | 1150L
    }
}
