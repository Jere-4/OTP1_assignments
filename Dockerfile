FROM maven:3-eclipse-temurin-17
WORKDIR /app
COPY pom.xml .
COPY . /app
RUN mvn package
CMD ["java", "-jar", "target/YOURJAR.jar"]