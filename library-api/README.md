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