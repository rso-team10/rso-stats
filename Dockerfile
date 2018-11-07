FROM openjdk:11-jre-slim

RUN mkdir /app

WORKDIR /app

ADD ./api/target/stats-api-1.0.0.jar /app

EXPOSE 8082

CMD java -jar stats-api-1.0.0.jar