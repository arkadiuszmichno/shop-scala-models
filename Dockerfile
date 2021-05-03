FROM ubuntu:18.04

#JAVA
RUN apt-get update &&\
	apt-get upgrade -y &&\
	apt-get install -y openjdk-8-jdk

ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
ENV PATH $JAVA_HOME/bin:$PATH

#WGET, GNUPG2
RUN apt update && apt install -y wget &&\
	apt install -y curl &&\
	apt install -y gnupg2

#SCALA
RUN wget http://scala-lang.org/files/archive/scala-2.12.8.deb &&\
	dpkg -i scala-2.12.8.deb &&\
	apt-get update &&\
	apt-get install scala

#SBT
RUN echo "deb https://dl.bintray.com/sbt/debian /" | tee -a /etc/apt/sources.list.d/sbt.list
RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823
RUN apt-get update &&\
	apt-get install -y sbt

#NODEJS WITH NPM
RUN curl -sL https://deb.nodesource.com/setup_15.x | bash -
RUN apt-get update &&\
	apt-get install -y nodejs
RUN npm install -g npm@latest

EXPOSE 3000
EXPOSE 9000

RUN useradd -ms /bin/bash amichno
RUN adduser amichno sudo

USER root
WORKDIR /home/amichno/
RUN mkdir /home/amichno/workshop/
WORKDIR /home/amichno/workshop/shop

VOLUME ["/home/amichno/workshop"]