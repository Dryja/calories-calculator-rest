package com.testing.calories.calculator.service

import com.testing.calories.calculator.dto.UserDTO
import com.testing.calories.calculator.model.UserDetailsEntity
import com.testing.calories.calculator.model.UserEntity
import com.testing.calories.calculator.repository.UserDetailsRepository
import com.testing.calories.calculator.repository.UserRepository
import spock.lang.Specification

class UserServiceTest extends Specification {

    UserService userService

    UserRepository userRepository = Mock()
    UserDetailsRepository userDetailsRepository = Mock()

    def setup() {
        userService = new UserService(userRepository, userDetailsRepository)
    }

    def "AddUser"() {
        given:
        def userDto = UserDTO.builder().email(email).goal(goal).build()
        def userEntity = UserEntity.builder().email(email).goal(goal).build()//UserMapper.toEntity(userDto)

        and:
        userRepository.findUserEntityByEmail(email) >> userEntity
        userRepository.save(userEntity) >> userEntity

        when:
        def result = userService.addUser(userDto)

        then:
        assert result.goal == goal
        assert result.email == email

        where:
        email            | goal
        "ds@sa.pl"       | "goal"
        "asdfa@asdaf.pl" | "goal"
    }

    def "RemoveUser"() {
        given:
        def userEntity = UserEntity.builder().goal(goal).email(email).build()

        and:
        userRepository.findUserEntityByEmail(email) >> userEntity

        and:
        userRepository.delete(userEntity) >> {}

        when:
        def result = userService.removeUser(email)

        then:
        assert result.goal == goal
        assert result.email == email

        where:
        email              | goal
        "nge@asdf.pl"      | "goal"
        "lonadsfa@asdf.pl" | "goal"
    }

    def "GetUserList"() {
        given:
        def userList = [
                UserEntity.builder().email("test@asd.pl").goal("goal").build(),
                UserEntity.builder().email("test@asdf.lk").goal("goal").build(),
                UserEntity.builder().email("testsdaf@sdaf.pl").goal("goal").build(),
                UserEntity.builder().email("test3asdf@sad.pl").goal("goal").build(),
                UserEntity.builder().email("test4@sa.pl").goal("goal").build(),
        ]

        and:
        userRepository.findAll() >> userList

        when:
        def result = userService.getUserList()

        then:
        assert result.size() == userList.size()
        assert result.get(0).email == userList.get(0).email

    }

    def "GetUser"() {
        given:
        def userEntity = UserEntity.builder().goal(goal).email(email).build()

        and:
        userRepository.findUserEntityByEmail(email) >> userEntity

        when:
        def result = userService.getUser(email)

        then:
        assert result.email == email
        assert result.goal == goal

        where:
        email        | goal
        "orange"     | "goal"
        "watermelon" | "goal"
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
