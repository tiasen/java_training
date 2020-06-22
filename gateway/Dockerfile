FROM openjdk:11-jdk

COPY build/libs/gateway-1.0-SNAPSHOT.jar /app/app.jar

VOLUME /root/.gradle

WORKDIR /app
EXPOSE 8088

CMD java -jar -Dspring.profiles.active=${ACTIVE_PROFILE:-local} app.jar