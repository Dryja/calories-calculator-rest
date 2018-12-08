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
        def userEntity = userRepository.save(UserEntity.builder().email(email).goal(goal).build())
        def user = userRepository.findById(userEntity.id).orElseThrow { _ -> new EntityNotFoundException("not found") }
        user.email == email
        user.goal == goal

        where:
        email               | goal
        "jakub@test.pl"     | "only fat and protein"
        "szymon@test.pl"    | "go hard or go home"
        "hubert@test.pl"    | "python"
        "mateusz@test.pl"   | "podlasie"
        "chaber@test.pl"    | "python4life"
        "jstar@test.pl"     | "magnesy"
        "krzysztof@test.pl" | "yerba mate"
    }

    def "UserRepository#count"() {
        given: "persisted data"
        entityManager.persist(
                UserEntity.builder()
                        .email("test@test.pl")
                        .goal("some goal for this person").build()
        )
        entityManager.persist(
                UserEntity.builder()
                        .email("test2@test.pl")
                        .goal("some goal for this person2").build()
        )
        entityManager.persist(
                UserEntity.builder()
                        .email("test3@test.pl")
                        .goal("some goal for this person3").build()
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
