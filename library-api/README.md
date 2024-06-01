# Library API

## How to Run Locally
1. Have at least java version 18.
2. Run the command ``mvn install``
3. Run the command ``mvn spring-boot:run``
4. The application will run locally at port 8099.

## How to Run with Docker
1. Go into the folder that has Dockerfile.
2. Run the command ``docker build -t library-api .``
3. Run the command `docker run -p 8099:8099 library-api`

## Access Important Links:
- Swagger: http://localhost:8099/swagger-ui/index.html
- H2 Database: http://localhost:8099/h2-console/login.jsp?jsessionid=52fec5b0158e4553668d05407ea4a576
  - Driver Class: org.h2.Driver
  - JDBC URL: jdbc:h2:mem:mydb
  - Username: sa
  - Password: password

## Default Users

| Email                    | Password |
|--------------------------|----------|
| bruno.hoffmann@email.com | 1234     |
| bel.cogo@email.com       | 4321     |
| joao.accorsi@email.com   | 1234     |
| taylor.swift@email.com   | 1234     |
