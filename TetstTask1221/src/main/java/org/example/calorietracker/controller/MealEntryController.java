package org.example.calorietracker.controller;

import org.example.calorietracker.model.MealEntry;
import org.example.calorietracker.service.MealEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/meal-entries")
@RequiredArgsConstructor
public class MealEntryController {
    private final MealEntryService mealEntryService;

    @PostMapping
    public ResponseEntity<MealEntry> createMealEntry(
            @RequestParam Long userId,
            @RequestParam Long mealId,
            @RequestParam(defaultValue = "1.0") Double servings) {
        return ResponseEntity.ok(mealEntryService.createMealEntry(userId, mealId, servings));
    }

    @GetMapping("/user/{userId}/day")
    public ResponseEntity<List<MealEntry>> getMealEntriesForDay(
            @PathVariable Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(mealEntryService.getMealEntriesForDay(userId, date));
    }

    @GetMapping("/user/{userId}/calories/today")
    public ResponseEntity<Integer> getTotalCaloriesForToday(@PathVariable Long userId) {
        return ResponseEntity.ok(mealEntryService.getTotalCaloriesForDay(userId));
    }

    @GetMapping("/user/{userId}/check-limit")
    public ResponseEntity<Boolean> checkDailyLimit(@PathVariable Long userId) {
        return ResponseEntity.ok(mealEntryService.isWithinDailyLimit(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMealEntry(@PathVariable Long id) {
        mealEntryService.deleteMealEntry(id);
        return ResponseEntity.ok().build();
    }
}