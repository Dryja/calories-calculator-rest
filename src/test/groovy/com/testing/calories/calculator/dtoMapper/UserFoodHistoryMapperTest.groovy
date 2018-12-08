package com.testing.calories.calculator.dtoMapper

import com.testing.calories.calculator.model.FoodEntity
import com.testing.calories.calculator.model.UserEntity
import com.testing.calories.calculator.model.UserFoodHistoryEntity
import spock.lang.Specification

import java.sql.Date

class UserFoodHistoryMapperTest extends Specification {

    def "maps toDTO properly from entity"() {
        expect:
        def date = new Date(dateOfConsumption[0], dateOfConsumption[1], dateOfConsumption[2]) // xDDD

        def userEntity = UserEntity.builder().email(email).build()
        def foodEntity = FoodEntity.builder().name(name).calories(calories).build()
        def userFoodHistoryEntity = UserFoodHistoryEntity.builder().dateOfConsumption(date).user(userEntity).food(foodEntity).build()

        def userFoodHistoryDto = UserFoodHistoryMapper.toDTO(userFoodHistoryEntity)
        assert userFoodHistoryDto.dateOfConsumption == date
        assert userFoodHistoryDto.calories == calories
        assert userFoodHistoryDto.email == email
        assert userFoodHistoryDto.food == name

        where:
        email               | name      | calories | dateOfConsumption
        "jakub@test.pl"     | "apple"   | 50L      | [2012, 11, 10]
        "szymon@test.pl"    | "orange"  | 50L      | [2011, 10, 10]
        "hubert@test.pl"    | "human"   | 50000L   | [2013, 1, 10]
        "mateusz@test.pl"   | "test"    | 50L      | [2015, 5, 10]
        "chaber@test.pl"    | "boar"    | 50000L   | [2022, 3, 10]
        "jstar@test.pl"     | "beer"    | 150L     | [2042, 4, 10]
        "krzysztof@test.pl" | "chicken" | 1150L    | [2052, 11, 10]
    }
}
