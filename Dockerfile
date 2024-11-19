FROM openjdk:17-jdk-alpine
RUN mkdir /app
WORKDIR /app
COPY target/*.jar app.jar
CMD ["java", "-jar", "app.jar"]