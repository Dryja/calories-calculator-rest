package com.testing.calories.calculator.service

import com.testing.calories.calculator.model.UserDetailsEntity
import com.testing.calories.calculator.model.UserEntity
import com.testing.calories.calculator.repository.UserDetailsRepository
import com.testing.calories.calculator.repository.UserRepository
import spock.lang.Specification

class UserServiceTest2 extends Specification {

    UserService userService

    UserRepository userRepository = Mock()
    UserDetailsRepository userDetailsRepository = Mock()

    def setup() {
        userService = new UserService(userRepository, userDetailsRepository)
    }

    def "GetEnergyRequirements"() {
        given:
        def userEntity = UserEntity.builder()
                .email("jakub@test.pl")
                .goal("gain")
                .build()

        def userDetailsEntity = UserDetailsEntity.builder()
                .sex("male")
                .height(180)
                .weight(70)
                .age(21)
                .physicalActivity("very")
                .user(userEntity)
                .build()

        userEntity.setUserDetails(userDetailsEntity)

        userRepository.findUserEntityByEmail(userEntity.email) >> userEntity
        userDetailsRepository.findUserDetailsEntityByUser(userEntity) >> userDetailsEntity

        when:
        def energyRequirements = userService.getEnergyRequirements(userEntity.email)

        then:
        assert energyRequirements == 3422
    }
}
