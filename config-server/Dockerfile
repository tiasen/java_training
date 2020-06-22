FROM openjdk:11-jdk

COPY build/libs/config-server-1.0-SNAPSHOT.jar /app/app.jar

VOLUME /root/.gradle

WORKDIR /app
EXPOSE 7000

CMD java -jar -Dspring.profiles.active=${ACTIVE_PROFILE:-local} app.jar