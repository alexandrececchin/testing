#!/usr/bin/env bash

mvn clean install

cd ./user-microservice/

mvn spring-boot:run -Dserver.port=8081 -Dspring.datasource.platform=e2e

cd ../account-microservice/
mvn spring-boot:run -Dserver.port=8080 -Dspring.datasource.platform=e2e