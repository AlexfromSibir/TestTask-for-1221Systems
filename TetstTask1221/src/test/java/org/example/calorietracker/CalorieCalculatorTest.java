package org.example.calorietracker;


import org.example.calorietracker.model.User;

import org.example.calorietracker.service.CalorieCalculator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CalorieCalculatorTest {

    @Autowired
    private CalorieCalculator calorieCalculator;

    @Test
    void calculateDailyCaloriesForMaleWeightLoss() {
        User user = new User();
        user.setGender(User.Gender.MALE);
        user.setAge(30);
        user.setWeight(80.0);
        user.setHeight(180.0);
        user.setGoal(User.Goal.WEIGHT_LOSS);

        int calories = calorieCalculator.calculateDailyCalories(user);
        assertTrue(calories > 0);
        assertTrue(calories < 3000);
    }

    @Test
    void calculateDailyCaloriesForFemaleMaintenance() {
        User user = new User();
        user.setGender(User.Gender.FEMALE);
        user.setAge(25);
        user.setWeight(65.0);
        user.setHeight(165.0);
        user.setGoal(User.Goal.MAINTENANCE);

        int calories = calorieCalculator.calculateDailyCalories(user);
        assertTrue(calories > 0);
        assertTrue(calories < 2500);
    }

    @Test
    void calculateDailyCaloriesForWeightGain() {
        User user = new User();
        user.setGender(User.Gender.MALE);
        user.setAge(35);
        user.setWeight(70.0);
        user.setHeight(175.0);
        user.setGoal(User.Goal.WEIGHT_GAIN);

        int calories = calorieCalculator.calculateDailyCalories(user);
        assertTrue(calories > 0);
        assertTrue(calories < 3500);
    }
}