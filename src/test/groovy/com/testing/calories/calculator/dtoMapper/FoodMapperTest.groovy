package com.testing.calories.calculator.dtoMapper

import com.testing.calories.calculator.model.FoodEntity
import com.testing.calories.calculator.model.UserFoodHistoryEntity
import spock.lang.Specification

import java.sql.Date


class FoodMapperTest extends Specification {

    def "toDTO maps properly from entity"() {

        expect:

        def foodEntity = FoodEntity.builder().name(name).calories(calories).build()

        if (dates) {
            dates.forEach { date ->
                foodEntity.addUserFoodHistoryEntity(UserFoodHistoryEntity.builder()
                        .dateOfConsumption(new Date(date))
                        .food(foodEntity)
                        .build())
            }
        }

        def foodDTO = FoodMapper.toDTO(foodEntity)
        assert foodDTO.calories == calories
        assert foodDTO.name == name

        if (dates) {
            assert foodDTO.userFoodHistoryList.size() == dates.size()
        }

        where:
        name      | calories | dates
        "apple"   | 50L      | [1544304002, 1544221202]
        "orange"  | 50L      | null
        "human"   | 50000L   | [1543702802, 1541715602, 1512771602]
        "test"    | 50L      | [1544311202]
        "boar"    | 50000L   | [1544394002]
        "beer"    | 150L     | [1544912402]
        "chicken" | 1150L    | [1546986002]
    }
}
