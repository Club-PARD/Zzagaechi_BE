FROM amazoncorretto:17-alpine

ARG JAR_FILE=build/libs/zzagaechi-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]

