[JAVA]: https://img.shields.io/badge/Java-%23ED8B00.svg?logo=openjdk&logoColor=white
[SPRING-BOOT]: https://img.shields.io/badge/Spring%20Boot-6DB33F?logo=springboot&logoColor=fff

<h1 style="font-weight: bold;">E-Commerce Shoes Store API</h1>

<p>The E-Commerce Shoes Store API is the server-side application for managing data and processes of an online shoes store. It provides REST APIs to handle core business logic, including product management, user authentication, and order processing.</p>

<h2 id="technologies">💻 Technologies</h2>

![Java][JAVA]
![Spring Boot][SPRING-BOOT]

<h2 id="started">🚀 Getting started</h2>

<h3>Prerequisites</h3>

- Java 17+
- Maven
- Git
- Docker

<h3>Cloning</h3>

First, clone the repository:

```shell
git clone git@github.com:marceloxhenrique/ecommerce-backend.git
```

<h3>Navigate to the project directory</h3>

```shell
cd <your-project-name>
```

<h3>Install the project</h3>

```shell
mvn clean install
```

<h3>Setup PostgreSQL Database with Docker Compose</h3>

<p>Start the PostgreSQL database using Docker Compose</p>

```shell
docker-compose up -d
```

<h3>Setup application.properties</h3>

```shell
spring.application.name=ecommerce
spring.sql.init.platform=postgres
spring.datasource.url=${POSTGRES_DATA_SOURCE}
spring.datasource.username=${POSTGRES_USER}
spring.datasource.password=${POSTGRES_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
```

<h3>Running the application</h3>

<p>The app run on port 8080 by default.</p>

```shell
mvn spring-boot:run
```
