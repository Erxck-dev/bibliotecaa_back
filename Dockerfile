# ETAPA 1: Construcción
FROM maven:3.9.6-eclipse-temurin-21-jammy AS build
WORKDIR /app
COPY . .
# Forzamos la compilación a Java 21 para evitar el error de la versión 25
RUN mvn clean package -DskipTests -Dmaven.compiler.release=21

# ETAPA 2: Ejecución
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
# Copiamos el JAR buscando el patrón del nombre que usa tu proyecto
COPY --from=build /app/target/biblioteca-*.jar app.jar

EXPOSE 8080
# Usamos un comando de arranque que nos de más info si falla
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
