#!/usr/bin/env bash

#if ! command -v mvn -version &> /dev/null
#then
#    sudo apt-get install maven
#fi
#
#mvn clean install

#docker build . --tag=telegram/nis:v1
docker buildx build --platform linux/amd64 . --tag=telegram/nis:v2

docker tag telegram/nis:v2 knockjkeee/test:telegram_kvedr
docker push knockjkeee/test:telegram_kvedr
