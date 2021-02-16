FROM openjdk:15
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} pet_service.jar
ENTRYPOINT ["java", "-jar", "/pet_service.jar"]