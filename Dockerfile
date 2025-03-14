FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY ./back/ .
RUN mvn clean package -DskipTests

# Etapa 2: Criando a imagem final
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

# Define a porta padrão
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]