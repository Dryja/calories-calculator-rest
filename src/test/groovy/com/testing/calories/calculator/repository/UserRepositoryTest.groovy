package com.testing.calories.calculator.repository

import com.testing.calories.calculator.model.FoodEntity
import com.testing.calories.calculator.model.UserEntity
import com.testing.calories.calculator.model.UserFoodHistoryEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import spock.lang.Specification

import javax.persistence.EntityNotFoundException
import java.sql.Date

@DataJpaTest
class UserRepositoryTest extends Specification {

    @Autowired
    TestEntityManager entityManager

    @Autowired
    UserRepository userRepository

    @Autowired
    FoodRepository foodRepository

    @Autowired
    UserFoodHistoryRepository userFoodHistoryRepository

    def "test adding user"() {
        expect:
        def userEntity = userRepository.save(UserEntity.builder().email(email).goal(goal).weight(weight).height(height).about(about).build())
        def user = userRepository.findById(userEntity.id).orElseThrow { _ -> new EntityNotFoundException("not found") }
        user.email == email
        user.goal == goal
        user.weight == weight
        user.height == height
        user.about == about

        where:
        email               | goal                   | weight | height | about
        "jakub@test.pl"     | "only fat and protein" | 120    | 120    | "I love script version of java"
        "szymon@test.pl"    | "go hard or go home"   | 60     | 200    | "I am super slim idk what I am doing here"
        "hubert@test.pl"    | "python"               | 80     | 180    | "Best guy in the universe"
        "mateusz@test.pl"   | "podlasie"             | 90     | 199    | "Podał się do ojca"
        "chaber@test.pl"    | "python4life"          | 76     | 170    | ""
        "jstar@test.pl"     | "magnesy"              | 90     | 172    | "Kayaks ftw"
        "krzysztof@test.pl" | "yerba mate"           | 96     | 182    | "mate yerba"
    }

    def "UserRepository#count"() {
        given: "persisted data"
        entityManager.persist(
                UserEntity.builder()
                        .email("test@test.pl")
                        .goal("some goal for this person")
                        .weight(1)
                        .height(1)
                        .about("first person text").build()
        )
        entityManager.persist(
                UserEntity.builder()
                        .email("test2@test.pl")
                        .goal("some goal for this person2")
                        .weight(2)
                        .height(2)
                        .about("second person text").build()
        )
        entityManager.persist(
                UserEntity.builder()
                        .email("test3@test.pl")
                        .goal("some goal for this person3")
                        .weight(3)
                        .height(3)
                        .about("third person text").build()
        )

        expect: "correct count in repository"
        userRepository.count() == 3L
    }

    def "adding history to user"() {
        given: "persisted data"
        def userFoodHistoryEntity = UserFoodHistoryEntity.builder().dateOfConsumption(new Date(2018, 11, 20)).build()
        def appleEntity = FoodEntity.builder().name("apple").calories(50L).build()
        def userEntity = UserEntity.builder().email("test3@test.pl").goal("some goal for this person3").build()

        appleEntity.addUserFoodHistoryEntity(userFoodHistoryEntity)
        userEntity.addUserFoodHistoryEntity(userFoodHistoryEntity)
        def apple = foodRepository.save(appleEntity)
        def user = userRepository.save(userEntity)

        expect: "user has record in foods"
        userRepository.findById(user.id).orElseThrow { _ -> new EntityNotFoundException("user not found") }.userFoodHistory.size() == 1
        foodRepository.findById(apple.id).orElseThrow { _ -> new EntityNotFoundException("food not found") }.userFoodHistory.size() == 1
        userFoodHistoryRepository.findAll().size() == 1
    }

}
