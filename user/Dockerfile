FROM openjdk:11-jdk

COPY build/libs/user-1.0-SNAPSHOT.jar /app/app.jar

VOLUME /root/.gradle

WORKDIR /app
EXPOSE 8082

CMD java -jar -Dspring.profiles.active=${ACTIVE_PROFILE:-local} app.jar