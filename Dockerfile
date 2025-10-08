# Root image
FROM amazoncorretto:21

# Arguments
ENV JAVA_ARGS="--spring.config.location=file:/app/configs/application.properties"

# Dir
WORKDIR /app

# Volumes
VOLUME /app/bots
VOLUME /app/configs
VOLUME /app/data

# Port
EXPOSE 8080

# Copys
COPY target/DiscordBotManager-1.3.2.jar /app/app.jar
COPY entrypoint.sh /app/entrypoint.sh
COPY src/main/resources/application.properties /app/configs/application.properties

# Permissões
RUN chmod +x /app/entrypoint.sh

# LF Checker
RUN sed -i 's/\r$//' /app/entrypoint.sh

# Starter
ENTRYPOINT ["/app/entrypoint.sh"]