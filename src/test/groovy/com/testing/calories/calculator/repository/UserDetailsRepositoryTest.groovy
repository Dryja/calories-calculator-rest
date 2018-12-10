package com.testing.calories.calculator.repository

import com.testing.calories.calculator.model.UserDetailsEntity
import com.testing.calories.calculator.model.UserEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import spock.lang.Specification

import javax.persistence.EntityNotFoundException

@DataJpaTest
class UserDetailsRepositoryTest extends Specification {

    @Autowired
    TestEntityManager entityManager

    @Autowired
    UserRepository userRepository

    @Autowired
    UserDetailsRepository userDetailsRepository

    def "test adding user details"() {
        expect:
        def userDetailsEntity = userDetailsRepository.save(UserDetailsEntity.builder()
                .sex(sex)
                .height(height)
                .weight(weight)
                .age(age)
                .physicalActivity(physicalActivity)
                .build())

        def userDetails = userDetailsRepository.findById(userDetailsEntity.id).orElseThrow { _ -> new EntityNotFoundException("not found") }

        assert userDetails.sex == sex
        assert userDetails.height == height
        assert userDetails.weight == weight
        assert userDetails.age == age
        assert userDetails.physicalActivity == physicalActivity

        where:
        sex      | height | weight | age | physicalActivity
        "male"   | 190    | 90     | 21  | "very"
        "female" | 170    | 50     | 19  | "lightly"
    }

    def "test UserDetailsRepository#count"() {
        given: "persisted data"
        entityManager.persist(
                UserDetailsEntity.builder()
                        .sex("male")
                        .height(180)
                        .weight(75)
                        .age(25)
                        .physicalActivity("moderately").build()
        )
        entityManager.persist(
                UserDetailsEntity.builder()
                        .sex("female")
                        .height(160)
                        .weight(50)
                        .age(21)
                        .physicalActivity("very").build()
        )

        expect: "correct count in repository"
        userDetailsRepository.count() == 2L
    }

    def "test adding userDetails to user"() {
        expect:
        def userDetailsEntity = UserDetailsEntity.builder()
                .sex(sex)
                .height(height)
                .weight(weight)
                .age(age)
                .physicalActivity(physicalActivity)
                .build()

        def userEntity = UserEntity.builder()
                .email(email)
                .goal(goal)
                .build()

        def userDetails = userDetailsRepository.save(userDetailsEntity)
        userEntity.setUserDetails(userDetails)

        def user = userRepository.save(userEntity)

        def details = userRepository.findById(user.id).orElseThrow { _ -> new EntityNotFoundException("not found") }
                .getUserDetails()

        assert userDetailsRepository.existsById(details.id)

        where:
        email            | goal     | sex      | height | weight | age | physicalActivity
        "julia@test.pl"  | "reduce" | "female" | 160    | 45     | 19  | "very"
        "szymon@test.pl" | "keep"   | "male"   | 182    | 76     | 26  | "lightly"
        "hubert@test.pl" | "gain"   | "male"   | 222    | 100    | 45  | "sedentary"
    }
}
