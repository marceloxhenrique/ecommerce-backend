FROM maven:3.8.6-amazoncorretto-17 AS build 

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package

FROM amazoncorretto:17-alpine-jdk 

WORKDIR /app

COPY --from=build /app/target/ecommerce-0.0.1.jar /app/

EXPOSE 8080

CMD ["java", "-jar", "ecommerce-0.0.1.jar"]