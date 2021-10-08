FROM adoptopenjdk:8u212-b04-jre-hotspot-bionic
ARG APP_NAME=auth-service-0.0.1-SNAPSHOT.jar
ARG PORT=8095
COPY target/$APP_NAME /app/app.jar
CMD ["java", "-jar", "/app/app.jar"]
EXPOSE $PORT