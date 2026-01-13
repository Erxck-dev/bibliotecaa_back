# ETAPA 1: Construcción
# Usamos Java 21 que es la versión estable más compatible
FROM maven:3.9.6-eclipse-temurin-21-jammy AS build
WORKDIR /app
COPY . .

# Modificamos el comando para que ignore si tu proyecto pide Java 25 
# y lo compile con la versión disponible
RUN mvn clean package -DskipTests -Dmaven.compiler.release=21

# ETAPA 2: Ejecución
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
