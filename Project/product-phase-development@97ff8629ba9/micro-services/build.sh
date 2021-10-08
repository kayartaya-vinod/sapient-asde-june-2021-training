docker-compose down

docker rmi micro-services_email-service
docker rmi micro-services_customer-service
docker rmi micro-services_auth-service
docker rmi micro-services_gateway-service
docker rmi micro-services_eureka-service
docker rmi micro-services_fluentd
docker rmi micro-services_vehicle-data-service

cd ./eureka-service
mvn clean package -DskipTests
cd ..

cd ./gateway-service
mvn clean package -DskipTests
cd ..

cd ./auth-service
mvn clean package -DskipTests
cd ..

cd ./customer-service
mvn clean package -DskipTests
cd ..

cd ./email-service
mvn clean package -DskipTests
cd ..

cd ./vehicle-service
mvn clean package -DskipTests
cd ..

cd ./vehicle-data-service
mvn clean package -DskipTests
cd ..

docker-compose up -d