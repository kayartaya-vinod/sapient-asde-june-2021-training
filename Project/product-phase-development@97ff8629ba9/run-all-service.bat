@echo off
start cmd /k "cd ./micro-services/eureka-service/ && mvn spring-boot:run"
start cmd /k "cd ./micro-services/gateway-service/ && mvn spring-boot:run"
start cmd /k "cd ./micro-services/auth-service/ && mvn spring-boot:run"
start cmd /k "cd ./micro-services/customer-service/ && mvn spring-boot:run"
start cmd /k "cd ./micro-services/email-service/ && mvn spring-boot:run"
start cmd /k "cd ./micro-services/vehicle-data-service/ && mvn spring-boot:run"
start cmd /k "cd ./micro-services/vehicle-service/ && mvn spring-boot:run"
start cmd /k "cd ./my-cars-solution && npm start"
