package com.testing.calories.calculator.dtoMapper

import com.testing.calories.calculator.model.UserDetailsEntity
import com.testing.calories.calculator.model.UserEntity
import spock.lang.Specification

class UserDetailsMapperTest extends Specification {
    def "toDto maps properly from entity"() {
        expect:
        def userEntity = UserEntity.builder()
                .email(email)
                .build()

        def userDetailsEntity = UserDetailsEntity.builder()
                .sex(sex)
                .height(height)
                .weight(weight)
                .age(age)
                .physicalActivity(physicalActivity)
                .user(userEntity)
                .build()

        def userDetailsDTO = UserDetailsMapper.toDTO(userDetailsEntity)
        assert userDetailsDTO.sex == sex
        assert userDetailsDTO.height == height
        assert userDetailsDTO.weight == weight
        assert userDetailsDTO.age == age
        assert userDetailsDTO.email == email

        where:
        email            | goal     | sex      | height | weight | age | physicalActivity
        "julia@test.pl"  | "reduce" | "female" | 160    | 45     | 19  | "very"
        "szymon@test.pl" | "keep"   | "male"   | 182    | 76     | 26  | "lightly"
        "hubert@test.pl" | "gain"   | "male"   | 222    | 100    | 45  | "sedentary"
    }
}
