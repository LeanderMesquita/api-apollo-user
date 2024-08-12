FROM openjdk:22-jdk-slim

# Definir o argumento para o JAR
ARG JAR_FILE=target/*.jar

# Copiar o JAR para o contêiner
COPY ${JAR_FILE} /app.jar

# Definir o diretório de trabalho
WORKDIR /ApolloUsuario

# Copiar o arquivo .env para o contêiner
COPY .env .env

# Criar um script de inicialização
RUN echo '#!/bin/sh' > /entrypoint.sh \
    && echo 'export $(grep -v "^#" .env | xargs)' >> /entrypoint.sh \
    && echo 'exec java -jar /app.jar' >> /entrypoint.sh \
    && chmod +x /entrypoint.sh

# Usar o script de inicialização como ENTRYPOINT
ENTRYPOINT ["/entrypoint.sh"]
