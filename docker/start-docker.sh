#!/bin/sh
cp ../theta-restapi/build/libs/restapi-1.0.0.jar ./
docker-compose build
docker-compose up
