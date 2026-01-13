FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY . .

# Esta línea es la que arregla el error 126 dándole permisos al archivo
RUN chmod +x mvnw

# Ahora sí podrá ejecutar el comando de instalación
RUN ./mvnw clean install -DskipTests

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "target/biblioteca-Grupo-1-0.0.1-SNAPSHOT.jar"]
