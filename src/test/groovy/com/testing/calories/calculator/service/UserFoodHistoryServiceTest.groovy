package com.testing.calories.calculator.service

import com.testing.calories.calculator.model.FoodEntity
import com.testing.calories.calculator.model.UserEntity
import com.testing.calories.calculator.model.UserFoodHistoryEntity
import com.testing.calories.calculator.repository.FoodRepository
import com.testing.calories.calculator.repository.UserFoodHistoryRepository
import com.testing.calories.calculator.repository.UserRepository
import spock.lang.Specification

import java.sql.Date

class UserFoodHistoryServiceTest extends Specification {

    UserFoodHistoryService userFoodHistoryService

    UserRepository userRepository = Mock()
    FoodRepository foodRepository = Mock()
    UserFoodHistoryRepository userFoodHistoryRepository = Mock()

    def setup() {
        this.userFoodHistoryService = new UserFoodHistoryService(userFoodHistoryRepository, userRepository, foodRepository)
    }

    def "AddFoodHistory should return new UserFoodHistoryEntity as UserFoodHistoryDTO"() {
        given: "entities"
        def userFoodHistoryEntity = UserFoodHistoryEntity.builder().dateOfConsumption(new Date(dateOfConsumption)).build()
        def userEntity = UserEntity.builder().email(email).goal(" ").build()
        def foodEntity = FoodEntity.builder().name(foodName).calories(calories).build()

        and: "mocked repositories"
        userRepository.findUserEntityByEmail(email) >> userEntity
        foodRepository.findFoodEntityByName(foodName) >> foodEntity

        userFoodHistoryRepository.save(userFoodHistoryEntity) >> userFoodHistoryEntity
        userRepository.save(userEntity) >> userEntity
        foodRepository.save(foodEntity) >> foodEntity

        when: "using UserFoodHistoryService#addFoodHistory"
        def userFoodHistoryDto = userFoodHistoryService.addFoodHistory(email, foodName, new Date(dateOfConsumption))

        then: "return proper UserFoodHistoryDTO"
        assert userFoodHistoryDto.food == foodName
        assert userFoodHistoryDto.email == email
        assert userFoodHistoryDto.calories == calories
        assert userFoodHistoryDto.dateOfConsumption == new Date(dateOfConsumption)

        and: "user and food both contain one element in userFoodHistory"
        assert userEntity.userFoodHistory.size() == 1
        assert foodEntity.userFoodHistory.size() == 1

        where: "String email, String foodName, Date dateOfConsumption"
        email                    | foodName | calories | dateOfConsumption
        "hubert@dryja.com"       | "apple"  | 10L      | 1543702802
        "mateusz@koczan.com"     | "orange" | 20L      | 1544311202
        "jakub@baczek.com"       | "orange" | 30L      | 1544394002
        "szymon@petruczynik.com" | "orange" | 0L       | 1544912402
        "krzysiek@berski.com"    | "orange" | 10L      | 1546986002
    }

    def "RemoveFoodHistory"() {
        given: "entities"
        def userFoodHistoryEntity = UserFoodHistoryEntity.builder().dateOfConsumption(new Date(dateOfConsumption)).build()
        def userEntity = UserEntity.builder().email(email).goal(" ").build()
        def foodEntity = FoodEntity.builder().name(foodName).calories(calories).build()

        userEntity.addUserFoodHistoryEntity(userFoodHistoryEntity)
        foodEntity.addUserFoodHistoryEntity(userFoodHistoryEntity)

        and: "mocked repositories"
        userRepository.findUserEntityByEmail(email) >> userEntity
        foodRepository.findFoodEntityByName(foodName) >> foodEntity

        userRepository.save(userEntity) >> userEntity
        foodRepository.save(foodEntity) >> foodEntity

        userFoodHistoryRepository.delete(userFoodHistoryEntity) >> {}

        when: "using UserFoodHistoryService#addFoodHistory"
        def userFoodHistoryDto = userFoodHistoryService.removeFoodHistory(email, foodName, new Date(dateOfConsumption))

        then: "return proper UserFoodHistoryDTO"
        assert userFoodHistoryDto.food == foodName
        assert userFoodHistoryDto.email == email
        assert userFoodHistoryDto.calories == calories
        assert userFoodHistoryDto.dateOfConsumption == new Date(dateOfConsumption)

        and: "user and food both contain 0 elements in userFoodHistory"
        assert userEntity.userFoodHistory.size() == 0
        assert foodEntity.userFoodHistory.size() == 0

        where: "String email, String foodName, Date dateOfConsumption"
        email                    | foodName | calories | dateOfConsumption
        "hubert@dryja.com"       | "apple"  | 10L      | 1543702802
        "mateusz@koczan.com"     | "orange" | 20L      | 1544311202
        "jakub@baczek.com"       | "orange" | 30L      | 1544394002
        "szymon@petruczynik.com" | "orange" | 0L       | 1544912402
        "krzysiek@berski.com"    | "orange" | 10L      | 1546986002
    }
}
