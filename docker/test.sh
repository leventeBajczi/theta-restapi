#!/bin/bash

IPs=(localhost 172.22.0.2 172.22.0.3 172.22.0.4)
names=(worker1 worker2 worker3)
PORT=8080
echo $IPs
for ip in ${IPs[@]}
do
echo "Sending theta to " $ip
curl --request PUT \
  --url http://$ip:$PORT/theta \
  --header 'Content-Type: multipart/form-data; boundary=---011000010111000001101001' \
  --header 'X-API-KEY: xApiKey' \
  --form binary=@/home/levente/Downloads/theta.zip \
  --form version=SampleVersion \
  --form commit=SampleCommit \
  --form description=SampleDescription \
  --form relativePath=theta/theta-start.sh

echo "Sending runexec to " $ip
curl --request PUT \
  --url http://$ip:$PORT/runexec \
  --header 'Content-Type: multipart/form-data; boundary=---011000010111000001101001' \
  --header 'X-API-KEY: xApiKey' \
  --form binary=@/home/levente/Documents/University/kotlin/theta-restapi/src/test/resources/benchexec.zip \
  --form version=SampleVersion \
  --form commit=SampleCommit \
  --form description=SampleDescription \
  --form relativePath=benchexec/bin/runexec
done

echo "Setting up worker2"
curl --request POST \
  --url http://${IPs[0]}:$PORT/workers \
  --header 'Content-Type: application/json' \
  --header 'X-API-KEY: xApiKey' \
  --data '{
	"address": "http://'${IPs[1]}:$PORT'",
	"name": "'${names[1]}'"
}'

echo "Setting up worker2"
curl --request POST \
  --url http://${IPs[0]}:$PORT/workers \
  --header 'Content-Type: application/json' \
  --header 'X-API-KEY: xApiKey' \
  --data '{
        "address": "http://'${IPs[2]}:$PORT'",
        "name": "'${names[2]}'"
}'

echo "Sending task"
curl --request POST \
  --url http://${IPs[0]}:$PORT/tasks \
  --header 'Content-Type: application/json' \
  --header 'X-API-KEY: xApiKey' \
  --data '{"input":{"inputs":[{"name":"inputfile.c","content":"dm9pZCByZWFjaF9lcnJvcigpe30KZXh0ZXJuIGludCBfX1ZFUklGSUVSX25vbmRldF9pbnQoKTsKaW50IG1haW4oKSB7CiAgICBpbnQgYSA9IF9fVkVSSUZJRVJfbm9uZGV0X2ludCgpOwogICAgaWYoIGEgJSAyICkgcmVhY2hfZXJyb3IoKTsKfQ=="}]},"userId":0,"parameters":["inputfile.c","--portfolio","COMPLEX"],"benchmark":{"useRunexec":true,"useScheduling":true,"resources":{"logical_cpu":2,"ram_G":1,"timeout_s":10}}}'
