FROM openjdk:8
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} fintech.jar
ENTRYPOINT ["java", "-jar", "fintech.jar"]