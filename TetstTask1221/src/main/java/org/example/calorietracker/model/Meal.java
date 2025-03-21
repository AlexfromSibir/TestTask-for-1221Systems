package org.example.calorietracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "meals")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название блюда не может быть пустым")
    private String name;

    @Min(value = 0, message = "Калории не могут быть отрицательными")
    @Column(name = "calories_per_serving")
    private Integer caloriesPerServing;

    @Min(value = 0, message = "Белки не могут быть отрицательными")
    @Max(value = 100, message = "Некорректное количество белков")
    private Double proteins;

    @Min(value = 0, message = "Жиры не могут быть отрицательными")
    @Max(value = 100, message = "Некорректное количество жиров")
    private Double fats;

    @Min(value = 0, message = "Углеводы не могут быть отрицательными")
    @Max(value = 100, message = "Некорректное количество углеводов")
    private Double carbohydrates;

}