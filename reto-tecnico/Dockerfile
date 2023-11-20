# Usa una imagen base de OpenJDK
FROM openjdk:17

# Copia el archivo JAR de tu aplicación al contenedor
COPY target/reto-tecnico-0.0.1-SNAPSHOT.jar /app.jar

# Expone el puerto en el que se ejecutará tu aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "/app.jar"]