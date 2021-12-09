FROM ubuntu:20.04
RUN apt update && apt install -y \
    software-properties-common \
    openjdk-11-jre-headless \
    udev libgomp1 libmpfr-dev unzip
RUN add-apt-repository ppa:sosy-lab/benchmarking -y
RUN apt update && apt install -y benchexec || true
RUN adduser root benchexec
RUN mkdir /theta
COPY restapi-1.0.0.jar /theta/restapi.jar
WORKDIR /theta
CMD ["java", "-jar", "restapi.jar"]
