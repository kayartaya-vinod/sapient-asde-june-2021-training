FROM openjdk:8-jre-alpine3.7

WORKDIR /vinod/apps

COPY ./target/java-docker-demo-1.0-SNAPSHOT.jar ./app.jar

COPY ./info.txt ./

CMD java -cp ./app.jar com.sapient.App