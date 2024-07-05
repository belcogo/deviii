# Software Engineering: Implementation and Testing
## Evaluative Assignment 2024/01

### Goal
Develop an app or website that allows people to exchange books with each other.

### Technologies
- Frontend: ReactNative.
- Backend: Java with SpringBoot.
- Database: h2.

### Pre requirements
#### Frontend
- Node version `20.15.0`.
#### Backend
- Java `18` or docker (to run the API with a docker container).

### How to emulate the app
**Make sure you have either a iOS or an Android emulator configured**
1. From the projects root folder `/responsive-library` run the command `npm i` to install the dependencies.
2. After that run `npm run start`.
4. Access the url `localhost:3000` from your browser

**To see the project as a responsive app**
1. Open the developer tools and click on the device tool bar to set the view to responsive

### How to run the back-end
```bash
# clone repository
git clone https://github.com/JoaoAccorsi/Web-Store.git

# run in folder library-api/src/main/java/com/unisinos/library/LibraryApiApplication.java
./mvnw spring-boot:run
```