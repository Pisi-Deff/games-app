FROM openjdk:12

RUN yum install -y postgresql

ENV DB_HOST db
ENV DB games
ENV DB_USER gameuser
ENV DB_PASSWORD gamepass

COPY wait-for-db.sh wait-for-db.sh
RUN chmod +x /wait-for-db.sh

COPY build/libs/games-api-0.0.1-SNAPSHOT.jar games-api.jar

#ENTRYPOINT ["java", "-jar", "games-api.jar"]
ENTRYPOINT ["./wait-for-db.sh", "java", "-jar", "games-api.jar"]

EXPOSE 8085/tcp
