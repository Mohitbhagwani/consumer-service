# For Java 17,
FROM openjdk:17-jdk-slim
WORKDIR /opt/app/consumer-service
COPY ./target/*.jar /opt/app/consumer-service/consumer-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar",".jar"]