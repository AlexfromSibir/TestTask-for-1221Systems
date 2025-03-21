package org.example.calorietracker.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.example.calorietracker.model.MealEntry;

@Repository
public interface MealEntryRepository extends JpaRepository<MealEntry, Long> {

    @Query(value = "SELECT COALESCE(SUM(me.total_calories), 0) FROM meal_entries me WHERE me.user_id = :userId AND DATE(me.eating_time) = CURRENT_DATE", nativeQuery = true)
    Integer getTotalCaloriesForToday(@Param("userId") Long userId);

    List<MealEntry> findByUserIdAndEatingTimeBetween(Long userId, LocalDateTime startOfDay, LocalDateTime endOfDay);
}