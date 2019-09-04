FROM openjdk:12

RUN yum install -y postgresql

ENV DB games
ENV DB_USER gameuser
ENV DB_PASSWORD gamepass

COPY build/libs/games-api-0.0.1-SNAPSHOT.jar games-api.jar
COPY wait-for-db.sh wait-for-db.sh
RUN chmod +x /wait-for-db.sh

#ENTRYPOINT ["java", "-jar", "games-api.jar"]
ENTRYPOINT ["./wait-for-db.sh", "db", "java", "-jar", "games-api.jar"]

EXPOSE 8085/tcp
