FROM openjdk:12

COPY build/libs/zuul-0.0.1-SNAPSHOT.jar zuul.jar

ENTRYPOINT ["java", "-jar", "zuul.jar"]

EXPOSE 8761/tcp
