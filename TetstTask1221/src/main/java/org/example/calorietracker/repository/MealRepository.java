package org.example.calorietracker.repository;

import org.example.calorietracker.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    boolean existsByName(String name);
}