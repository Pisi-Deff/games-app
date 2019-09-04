FROM openjdk:12

ADD /build/libs/eureka-0.0.1-SNAPSHOT.jar eureka.jar

ENTRYPOINT ["java", "-jar", "eureka.jar"]

EXPOSE 8761/tcp
