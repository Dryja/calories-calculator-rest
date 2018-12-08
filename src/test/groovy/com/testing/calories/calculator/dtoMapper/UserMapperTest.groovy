package com.testing.calories.calculator.dtoMapper

import com.testing.calories.calculator.model.UserEntity
import com.testing.calories.calculator.model.UserFoodHistoryEntity
import spock.lang.Specification

import java.sql.Date

class UserMapperTest extends Specification {
    def "toDTO maps properly from entity"() {

        expect:

        def userEntity = UserEntity.builder().email(email).goal(goal).build()

        if (dates) {
            dates.forEach { date ->
                userEntity.addUserFoodHistoryEntity(UserFoodHistoryEntity.builder()
                        .dateOfConsumption(new Date(date))
                        .user(userEntity).build())
            }
        }

        def userDTO = UserMapper.toDTO(userEntity)
        assert userDTO.email == email
        assert userDTO.goal == goal

        if (dates) {
            assert userDTO.userFoodHistoryList.size() == dates.size()
        }

        where:
        email               | goal                   | dates
        "jakub@test.pl"     | "only fat and protein" | [1544304002, 1544221202]
        "szymon@test.pl"    | "go hard or go home"   | null
        "hubert@test.pl"    | "python"               | [1543702802, 1541715602, 1512771602]
        "mateusz@test.pl"   | "podlasie"             | [1544311202]
        "chaber@test.pl"    | "python4life"          | [1544394002]
        "jstar@test.pl"     | "magnesy"              | [1544912402]
        "krzysztof@test.pl" | "yerba mate"           | [1546986002]
    }
}
