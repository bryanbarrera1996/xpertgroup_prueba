FROM eclipse-temurin:8-jdk
EXPOSE 8080

ARG JAR_FILE=target/*.jar

# Crear grupo y usuario de sistema
RUN groupadd --system pipeline \
 && useradd --system --create-home --gid pipeline k8s-pipeline
COPY ${JAR_FILE} /home/k8s-pipeline/app.jar

# Cambiar a usuario no root
USER k8s-pipeline

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/home/k8s-pipeline/app.jar"]
