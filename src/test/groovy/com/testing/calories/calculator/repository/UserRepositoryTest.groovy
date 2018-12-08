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
        def apple = foodRepository.save(FoodEntity.builder().name("apple").calories(50L).build())
        def user = userRepository.save(
                UserEntity.builder()
                        .email("test3@test.pl")
                        .goal("some goal for this person3").build()
        )
        userFoodHistoryRepository.save(UserFoodHistoryEntity.builder().food(apple).user(user).dateOfConsumption(new Date(2018, 11, 20)).build())

        expect: "user has record in foods"
        userRepository.findById(user.id).orElseThrow { _ -> new EntityNotFoundException("user not found") }.foods.size() == 1
    }

}
