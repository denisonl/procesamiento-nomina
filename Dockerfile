#Dockerfile para ejecutar la app java 8+ & maven
FROM maven:3-jdk-8
    WORKDIR /app
    COPY pom.xml .
    COPY src ./src
    RUN mvn clean install
    CMD ["sh", "-c", "java -jar target/procesamiento-nomina-1.0-0.jar && ls -l src/main/resources/output"]
