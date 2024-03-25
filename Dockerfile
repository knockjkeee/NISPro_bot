FROM openjdk:21
LABEL authors="knockjkeee"
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8008

ENTRYPOINT ["java","-jar","/app.jar"]