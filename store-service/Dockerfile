FROM openjdk:15
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} store_service.jar
ENTRYPOINT ["java", "-jar", "/store_service.jar"]