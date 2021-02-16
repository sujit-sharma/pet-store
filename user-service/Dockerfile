FROM openjdk:15
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} user_service.jar
ENTRYPOINT ["java", "-jar", "/user_service.jar"]