FROM ubuntu:latest as build

RUN apt-get update \
    && apt-get install -y openjdk-17-jdk maven

WORKDIR /app

COPY . .

RUN mvn clean package

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/ecommerce-0.0.1.jarr .

EXPOSE 8080

CMD ["java", "-jar", "/app/ecommerce-0.0.1.jar"]

