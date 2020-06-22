#!/usr/bin/env bash

docker volume create --name=java_training_db

docker run --rm -d \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=java_training \
  -e MYSQL_USER=training \
  -e MYSQL_PASSWORD=training \
  -v java_training_db:/var/lib/mysql \
  -p 3306:3306 \
  mysql

