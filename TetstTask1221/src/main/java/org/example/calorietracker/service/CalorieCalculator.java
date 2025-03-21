package org.example.calorietracker.service;

import org.example.calorietracker.model.User;
import org.springframework.stereotype.Service;

@Service
public class CalorieCalculator {

    public int calculateDailyCalories(User user) {

        double bmr;
        if (user.getGender() == User.Gender.MALE) {
            bmr = 88.36 + (13.4 * user.getWeight()) + (4.8 * user.getHeight()) - (5.7 * user.getAge());
        } else {
            bmr = 447.6 + (9.2 * user.getWeight()) + (3.1 * user.getHeight()) - (4.3 * user.getAge());
        }

        double activityMultiplier = 1.2;

        switch (user.getGoal()) {
            case WEIGHT_LOSS:
                bmr *= activityMultiplier * 0.85;
                break;
            case WEIGHT_GAIN:
                bmr *= activityMultiplier * 1.15;
                break;
            default:
                bmr *= activityMultiplier;
        }

        return (int) bmr;
    }
}