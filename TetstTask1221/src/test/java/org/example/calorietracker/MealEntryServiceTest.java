package org.example.calorietracker;

import org.example.calorietracker.model.Meal;
import org.example.calorietracker.model.MealEntry;
import org.example.calorietracker.model.User;
import org.example.calorietracker.repository.MealEntryRepository;
import org.example.calorietracker.service.MealEntryService;
import org.example.calorietracker.service.MealService;
import org.example.calorietracker.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MealEntryServiceTest {

    @Mock
    private MealEntryRepository mealEntryRepository;

    @Mock
    private UserService userService;

    @Mock
    private MealService mealService;

    @InjectMocks
    private MealEntryService mealEntryService;

    @Test
    void createMealEntry_ShouldCalculateTotalCalories() {
        User user = new User();
        user.setId(1L);
        user.setDailyCalories(2000);

        Meal meal = new Meal();
        meal.setId(1L);
        meal.setCaloriesPerServing(500);

        when(userService.getUserById(1L)).thenReturn(user);
        when(mealService.getMealById(1L)).thenReturn(meal);
        when(mealEntryRepository.save(any(MealEntry.class))).thenAnswer(i -> i.getArgument(0));

        MealEntry entry = mealEntryService.createMealEntry(1L, 1L, 2.0);

        assertNotNull(entry);
        assertEquals(1000, entry.getTotalCalories());
        assertEquals(2.0, entry.getServings());
        verify(mealEntryRepository).save(entry);
    }

    @Test
    void getMealEntriesForDay_ShouldReturnEntries() {
        LocalDate date = LocalDate.now();
        List<MealEntry> expectedEntries = Arrays.asList(
                new MealEntry(),
                new MealEntry()
        );

        when(mealEntryRepository.findByUserIdAndEatingTimeBetween(
                anyLong(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(expectedEntries);

        List<MealEntry> entries = mealEntryService.getMealEntriesForDay(1L, date);

        assertEquals(expectedEntries, entries);
        verify(mealEntryRepository).findByUserIdAndEatingTimeBetween(
                anyLong(), any(LocalDateTime.class), any(LocalDateTime.class));
    }

    @Test
    void isWithinDailyLimit_ShouldReturnTrue_WhenUnderLimit() {
        User user = new User();
        user.setDailyCalories(2000);

        when(userService.getUserById(1L)).thenReturn(user);
        when(mealEntryRepository.getTotalCaloriesForToday(1L)).thenReturn(1500);

        boolean result = mealEntryService.isWithinDailyLimit(1L);

        assertTrue(result);
    }

    @Test
    void isWithinDailyLimit_ShouldReturnFalse_WhenOverLimit() {
        User user = new User();
        user.setDailyCalories(2000);

        when(userService.getUserById(1L)).thenReturn(user);
        when(mealEntryRepository.getTotalCaloriesForToday(1L)).thenReturn(2500);

        boolean result = mealEntryService.isWithinDailyLimit(1L);

        assertFalse(result);
    }
}