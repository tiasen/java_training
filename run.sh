#!/usr/bin/env bash

docker volume create --name=java_training_db
docker volume create --name=java-training-user-gradle-cache
docker volume create --name=java-training-gateway-gradle-cache
docker volume create --name=java-training-erur-gradle-cache
docker volume create --name=java-training-config-gradle-cache
docker volume create --name=java-training-email-gradle-cache
docker volume create --name=java-training-eureka-gradle-cache


docker-compose up