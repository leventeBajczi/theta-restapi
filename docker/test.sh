#!/bin/bash

IPs=(139.162.179.160 45.79.249.213 139.162.142.71)
names=(worker1 worker2 worker3)
PORT=8080
input=../theta-restapi/src/test/resources/input.c

# Uncomment for theta/runexec reinit
# for ip in ${IPs[@]}
# do
# echo "Sending theta to " $ip
# curl --request PUT \
#   --url http://$ip:$PORT/theta \
#   --header 'Content-Type: multipart/form-data; boundary=---011000010111000001101001' \
#   --header 'X-API-KEY: xApiKey' \
#   --form binary=@../theta-restapi/src/test/resources/theta.zip \
#   --form version=SampleVersion \
#   --form commit=SampleCommit \
#   --form description=SampleDescription \
#   --form relativePath=theta/theta-start.sh
# 
# echo "Sending runexec to " $ip
# curl --request PUT \
#   --url http://$ip:$PORT/runexec \
#   --header 'Content-Type: multipart/form-data; boundary=---011000010111000001101001' \
#   --header 'X-API-KEY: xApiKey' \
#   --form binary=@../theta-restapi/src/test/resources/benchexec.zip \
#   --form version=SampleVersion \
#   --form commit=SampleCommit \
#   --form description=SampleDescription \
#   --form relativePath=benchexec/bin/runexec
# done

echo "Setting up worker2"
curl --request POST \
  --url http://${IPs[0]}:$PORT/workers \
  --header 'Content-Type: application/json' \
  --header 'X-API-KEY: xApiKey' \
  --data '{
	"address": "http://'${IPs[1]}:$PORT'",
	"name": "'${names[1]}'"
}' > /dev/null 2>&1

echo "Setting up worker2"
curl --request POST \
  --url http://${IPs[0]}:$PORT/workers \
  --header 'Content-Type: application/json' \
  --header 'X-API-KEY: xApiKey' \
  --data '{
        "address": "http://'${IPs[2]}:$PORT'",
        "name": "'${names[2]}'"
}' > /dev/null 2>&1


base64task=$(base64 -w 0 ../theta-restapi/src/test/resources/input.c)

echo "Sending task #1"
id1=$(curl --request POST \
  --url http://${IPs[0]}:$PORT/tasks \
  --header 'Content-Type: application/json' \
  --header 'X-API-KEY: xApiKey' \
  --data '{"input":{"inputs":[{"name":"inputfile.c","content":"'$base64task'"}]},"userId":0,"parameters":["inputfile.c","--portfolio","COMPLEX"],"benchmark":{"useRunexec":true,"useScheduling":true,"resources":{"logical_cpu":2,"ram_G":1,"timeout_s":10}}}' 2>/dev/null |  jq -r ".id")

echo "Sending task #2"
id2=$(curl --request POST \
  --url http://${IPs[0]}:$PORT/tasks \
  --header 'Content-Type: application/json' \
  --header 'X-API-KEY: xApiKey' \
  --data '{"input":{"inputs":[{"name":"inputfile.c","content":"'$base64task'"}]},"userId":0,"parameters":["inputfile.c","--portfolio","COMPLEX"],"benchmark":{"useRunexec":true,"useScheduling":true,"resources":{"logical_cpu":2,"ram_G":1,"timeout_s":10}}}' 2>/dev/null |  jq -r ".id")

echo "Waiting for tasks to finish..."
sleep 5
echo "=============================="
printf "$(curl --request GET \
  --url http://${IPs[0]}:$PORT/tasks/$id1 \
  --header 'X-API-KEY: xApiKey' 2>/dev/null  |  jq -r ".doneStatus.stdout")"
  
echo
echo "=============================="
echo

printf "$(curl --request GET \
  --url http://${IPs[0]}:$PORT/tasks/$id2 \
  --header 'X-API-KEY: xApiKey' 2>/dev/null  |  jq -r ".doneStatus.stdout")"
