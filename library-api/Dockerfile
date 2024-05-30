# Usar a imagem base do Maven para construir o projeto
FROM maven:3.8.5-openjdk-18-slim AS build

# Definir o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiar os arquivos do projeto para o contêiner
COPY . .

# Construir o projeto
RUN mvn clean package

# Usar a imagem base do OpenJDK 18 para rodar a aplicação
FROM openjdk:18-jdk

# Definir o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiar o JAR construído da etapa anterior para o diretório de trabalho
COPY --from=build /app/target/**.jar /app/app.jar

# Expor a porta que a aplicação vai usar
EXPOSE 8099

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
