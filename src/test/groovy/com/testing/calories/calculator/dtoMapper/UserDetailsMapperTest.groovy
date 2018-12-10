package com.testing.calories.calculator.dtoMapper

import com.testing.calories.calculator.model.UserEntity
import com.testing.calories.calculator.model.UserFoodHistoryEntity
import spock.lang.Specification

import java.sql.Date

class UserDetailsMapperTest extends Specification {
    def "toDTO maps properly from entity"() {

        expect:

        def userEntity = UserEntity.builder().email(email).goal(goal).weight(weight).height(height).about(about).build()

        if (dates) {
            dates.forEach { date ->
                userEntity.addUserFoodHistoryEntity(UserFoodHistoryEntity.builder()
                        .dateOfConsumption(new Date(date))
                        .user(userEntity).build())
            }
        }

        def userDTO = UserDetailsMapper.toDTO(userEntity)
        assert userDTO.email == email
        assert userDTO.goal == goal
        assert userDTO.weight == weight
        assert userDTO.height == height
        assert userDTO.about == about
        if (dates) {
            assert userDTO.userFoodHistoryList.size() == dates.size()
        }

        where:
        email            | goal                   | dates                                | weight | height | about
        "jakub@test.pl"  | "only fat and protein" | [1544304002, 1544221202]             | 80     | 180    | "x"
        "szymon@test.pl" | "go hard or go home"   | null                                 | 60     | 160    | "d"
        "hubert@test.pl" | "python"               | [1543702802, 1541715602, 1512771602] | 80     | 180    | ""
    }
}
