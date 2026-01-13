# Paso 1: Usar Maven para construir el proyecto
FROM maven:3.9.6-eclipse-temurin-21-jammy AS build
WORKDIR /app
COPY . .
# Usamos el 'mvn' del sistema en lugar del './mvnw' que falla
RUN mvn clean install -DskipTests

# Paso 2: Usar una imagen ligera para ejecutar la app
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
# Copiamos solo el resultado (el .jar) del paso anterior
COPY --from=build /app/target/biblioteca-Grupo-1-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
