FROM openjdk:17-jdk-slim-buster AS builder

WORKDIR /app
COPY . .

RUN chmod a+x mvnw && \
./mvnw package

ENTRYPOINT java -jar /app/target/demo-*.jar