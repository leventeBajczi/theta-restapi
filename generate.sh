#!/bin/bash
openapi-generator-cli generate -g kotlin-spring -o theta-restapi-2 -i api.yaml --additional-properties=apiPackage=hu.bme.mit.theta.restapi.api,artifactId=restapi,basePackage=hu.bme.mit.theta.restapi,groupId=hu.bme.mit.theta,modelPackage=hu.bme.mit.theta.restapi.model,packageName=hu.bme.mit.theta.restapi,reactive=true,serviceInterface=true,useTags=true
sed -i "s/kotlinxCoroutinesVersion=\"1\.2\.0\"/kotlinxCoroutinesVersion=\"1\.6\.0\-RC\"/g; s/kotlinVersion = \"1\.3\.30\"/kotlinVersion = \"1\.6\.0\"/g; s/jvmTarget = \"1\.8\"/jvmTarget = \"11\"/g" theta-restapi/build.gradle.kts
rm theta-restapi/{pom.xml,README.md}
