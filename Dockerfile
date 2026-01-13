# ETAPA 1: Construcción (Build)
FROM maven:3.9.6-eclipse-temurin-21-jammy AS build
WORKDIR /app
# Copiamos solo el pom primero para descargar librerías (más rápido)
COPY pom.xml .
RUN mvn dependency:go-offline
# Copiamos el resto del código y compilamos
COPY . .
RUN mvn clean package -DskipTests

# ETAPA 2: Ejecución (Runtime)
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
# Buscamos cualquier archivo .jar en la carpeta target y lo renombramos a app.jar
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
