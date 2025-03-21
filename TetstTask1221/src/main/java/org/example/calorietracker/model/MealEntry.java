package org.example.calorietracker.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "meal_entries")
public class MealEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "meal_id", nullable = false)
    private Meal meal;

    @CreationTimestamp
    @Column(name = "eating_time")
    private LocalDateTime eatingTime;

    @Column(name = "servings")
    private Double servings = 1.0;

    @Column(name = "total_calories")
    private Integer totalCalories;
}