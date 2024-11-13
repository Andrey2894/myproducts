# Используем базовый образ с OpenJDK
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию в контейнере
WORKDIR /app

# Копируем файл .jar, скомпилированный вашим билд-инструментом (замените "myproduct.jar" на фактическое имя файла)
COPY target/myProducts-0.0.1-SNAPSHOT.jar /app/myproduct.jar

# Запуск приложения
ENTRYPOINT ["java", "-jar", "/app/myproduct.jar"]
