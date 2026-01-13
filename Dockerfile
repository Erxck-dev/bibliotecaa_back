# Usamos una imagen de Java 21 (o 25 si está disponible)
FROM eclipse-temurin:21-jdk-jammy

# Directorio de trabajo
WORKDIR /app

# Copiamos el archivo pom.xml y el código fuente
COPY . .

# Construimos la aplicación saltando los tests para ir más rápido
RUN ./mvnw clean install -DskipTests

# Exponemos el puerto
EXPOSE 8080

# Comando para arrancar la app
ENTRYPOINT ["java", "-jar", "target/biblioteca-Grupo-1-0.0.1-SNAPSHOT.jar"]
