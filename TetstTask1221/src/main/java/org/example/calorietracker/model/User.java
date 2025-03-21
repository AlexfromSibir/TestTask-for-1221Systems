package org.example.calorietracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Имя не может быть пустым")
    private String name;

    @Email(message = "Некорректный email")
    @NotBlank(message = "Email не может быть пустым")
    @Column(unique = true)
    private String email;

    @Min(value = 1, message = "Возраст должен быть больше 0")
    @Max(value = 150, message = "Некорректный возраст")
    private Integer age;

    @Min(value = 30, message = "Вес должен быть больше 30 кг")
    @Max(value = 300, message = "Вес должен быть меньше 300 кг")
    private Double weight;

    @Min(value = 50, message = "Рост должен быть больше 100 см")
    @Max(value = 250, message = "Рост должен быть меньше 250 см")
    private Double height;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Goal goal;

    @Column(name = "daily_calories")
    private Integer dailyCalories;

    public enum Gender {
        MALE, FEMALE
    }

    public enum Goal {
        WEIGHT_LOSS,
        MAINTENANCE,
        WEIGHT_GAIN
    }
}