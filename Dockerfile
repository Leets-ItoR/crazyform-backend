FROM gradle:7.6.1-jdk17 as builder

COPY / /workspace
WORKDIR /workspace

RUN chmod +x gradlew && ./gradlew clean build

FROM eclipse-temurin:17

COPY --from=builder /workspace/build/libs/crazyform-0.0.1-SNAPSHOT.jar ./app.jar

ENV TZ Asia/Seoul
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
