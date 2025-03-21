package org.example.calorietracker.service;

import org.example.calorietracker.model.Meal;
import org.example.calorietracker.repository.MealRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MealService {
    private final MealRepository mealRepository;

    @Transactional
    public Meal createMeal(Meal meal) {
        if (mealRepository.existsByName(meal.getName())) {
            throw new IllegalArgumentException("Блюдо с таким названием уже существует");
        }
        return mealRepository.save(meal);
    }

    public Meal getMealById(Long id) {
        return mealRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Блюдо не найдено"));
    }

    @Transactional
    public Meal updateMeal(Long id, Meal meal) {
        Meal existingMeal = getMealById(id);
        existingMeal.setName(meal.getName());
        existingMeal.setCaloriesPerServing(meal.getCaloriesPerServing());
        existingMeal.setProteins(meal.getProteins());
        existingMeal.setFats(meal.getFats());
        existingMeal.setCarbohydrates(meal.getCarbohydrates());
        return mealRepository.save(existingMeal);
    }

    public void deleteMeal(Long id) {
        if (!mealRepository.existsById(id)) {
            throw new EntityNotFoundException("Блюдо не найдено");
        }
        mealRepository.deleteById(id);
    }
}