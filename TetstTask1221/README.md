# Calorie Tracker API

REST API сервис для отслеживания дневной нормы калорий и учета съеденных блюд.

## Функциональность

- Управление пользователями (создание, чтение, обновление, удаление)
- Автоматический расчет дневной нормы калорий по формуле Харриса-Бенедикта
- Управление блюдами (создание, чтение, обновление, удаление)
- Учет приемов пищи
- Отчеты по калориям и приемам пищи

## Технологии

- Java 17
- Spring Boot 3.2.3
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok
- JUnit 5
- Mockito

## Требования

- Java 17 или выше
- PostgreSQL 12 или выше
- Maven 3.6 или выше

## Установка и запуск

1. Клонируйте репозиторий:
```bash
git clone https://github.com/yourusername/calorie-tracker.git
cd calorie-tracker
```

2. Создайте базу данных PostgreSQL:
```sql
CREATE DATABASE calorie_tracker;
```

3. Настройте подключение к базе данных в файле `src/main/resources/application.properties`

4. Соберите проект:
```bash
mvn clean install
```

5. Запустите приложение:
```bash
mvn spring-boot:run
```

## API Endpoints

### Пользователи

- POST `/api/users` - Создание пользователя
- GET `/api/users/{id}` - Получение пользователя по ID
- PUT `/api/users/{id}` - Обновление пользователя
- DELETE `/api/users/{id}` - Удаление пользователя

### Блюда

- POST `/api/meals` - Создание блюда
- GET `/api/meals/{id}` - Получение блюда по ID
- PUT `/api/meals/{id}` - Обновление блюда
- DELETE `/api/meals/{id}` - Удаление блюда

### Приемы пищи

- POST `/api/meal-entries` - Создание записи о приеме пищи
- GET `/api/meal-entries/user/{userId}/day` - Получение приемов пищи за день
- GET `/api/meal-entries/user/{userId}/calories/today` - Получение суммы калорий за день
- GET `/api/meal-entries/user/{userId}/check-limit` - Проверка соблюдения дневной нормы
- DELETE `/api/meal-entries/{id}` - Удаление записи о приеме пищи

## Тестирование

Для запуска тестов выполните:
```bash
mvn test
```

