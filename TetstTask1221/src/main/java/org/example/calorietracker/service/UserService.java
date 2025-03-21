package org.example.calorietracker.service;

import org.example.calorietracker.model.User;
import org.example.calorietracker.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CalorieCalculator calorieCalculator;

    @Transactional
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Пользователь с таким email уже существует");
        }
        user.setDailyCalories(calorieCalculator.calculateDailyCalories(user));
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
    }

    @Transactional
    public User updateUser(Long id, User user) {
        User existingUser = getUserById(id);
        existingUser.setName(user.getName());
        existingUser.setAge(user.getAge());
        existingUser.setWeight(user.getWeight());
        existingUser.setHeight(user.getHeight());
        existingUser.setGender(user.getGender());
        existingUser.setGoal(user.getGoal());
        existingUser.setDailyCalories(calorieCalculator.calculateDailyCalories(existingUser));
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("Пользователь не найден");
        }
        userRepository.deleteById(id);
    }
}