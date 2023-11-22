FROM maven:3.8.6-jdk-8
COPY ./. /
RUN echo "deb http://old-releases.ubuntu.com/ubuntu/ raring main restricted universe multiverse" > /etc/apt/sources.list.d/ia32-libs-raring.list
RUN apt-get install -y apt-transport-https

RUN apt-key adv --keyserver keyserver.ubuntu.com --recv-keys 40976EAF437D05B5
RUN apt-key adv --keyserver keyserver.ubuntu.com --recv-keys 3B4FE6ACC0B21F32

RUN apt-get update --allow-releaseinfo-change
RUN apt-get -y install lib32z1

RUN mvn clean
RUN mvn test