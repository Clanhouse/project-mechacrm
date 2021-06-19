FROM openjdk:8u212-jdk-alpine3.9
ADD backend/target/backend-1.0.0.jar .
EXPOSE 8080
CMD java -jar backend-1.0.0.jar