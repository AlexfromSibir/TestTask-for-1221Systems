package org.example.calorietracker.service;

import org.example.calorietracker.model.MealEntry;
import org.example.calorietracker.model.User;
import org.example.calorietracker.repository.MealEntryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MealEntryService {
    private final MealEntryRepository mealEntryRepository;
    private final UserService userService;
    private final MealService mealService;

    @Transactional
    public MealEntry createMealEntry(Long userId, Long mealId, Double servings) {
        User user = userService.getUserById(userId);
        var meal = mealService.getMealById(mealId);

        MealEntry entry = new MealEntry();
        entry.setUser(user);
        entry.setMeal(meal);
        entry.setServings(servings);
        entry.setEatingTime(LocalDateTime.now());
        entry.setTotalCalories((int) (meal.getCaloriesPerServing() * servings));

        return mealEntryRepository.save(entry);
    }

    public List<MealEntry> getMealEntriesForDay(Long userId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);
        return mealEntryRepository.findByUserIdAndEatingTimeBetween(userId, startOfDay, endOfDay);
    }

    public Integer getTotalCaloriesForDay(Long userId) {
        return mealEntryRepository.getTotalCaloriesForToday(userId);
    }

    public boolean isWithinDailyLimit(Long userId) {
        User user = userService.getUserById(userId);
        Integer totalCalories = getTotalCaloriesForDay(userId);
        return totalCalories != null && totalCalories <= user.getDailyCalories();
    }

    public void deleteMealEntry(Long id) {
        if (!mealEntryRepository.existsById(id)) {
            throw new EntityNotFoundException("Запись о приеме пищи не найдена");
        }
        mealEntryRepository.deleteById(id);
    }
}