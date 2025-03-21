package org.example.calorietracker.controller;

import org.example.calorietracker.model.Meal;
import org.example.calorietracker.service.MealService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meals")
@RequiredArgsConstructor
public class MealController {
    private final MealService mealService;

    @PostMapping
    public ResponseEntity<Meal> createMeal(@Valid @RequestBody Meal meal) {
        return ResponseEntity.ok(mealService.createMeal(meal));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meal> getMeal(@PathVariable Long id) {
        return ResponseEntity.ok(mealService.getMealById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Meal> updateMeal(@PathVariable Long id, @Valid @RequestBody Meal meal) {
        return ResponseEntity.ok(mealService.updateMeal(id, meal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeal(@PathVariable Long id) {
        mealService.deleteMeal(id);
        return ResponseEntity.ok().build();
    }
}