#!/bin/bash

echo "building jar file for rest-endpoint"
cd rest-endpoint
mvn clean package -DskipTests=true
echo "building docker image for rest-endpoint"
docker build --tag learnwithvinod/rest-endpoint:latest .
cd ..

echo "building jar file for reporitng-service"
cd reporting-service
mvn clean package -DskipTests=true
echo "building docker image for reporitng-service"
docker build --tag learnwithvinod/reporting-service:latest .
cd ..

echo "All done!"