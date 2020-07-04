FROM maven:3-openjdk-11 as builder

WORKDIR /kotlin
COPY ./src ./src
COPY ./pom.xml ./pom.xml
RUN mvn package

FROM openjdk:11

WORKDIR /app
COPY --from=builder /kotlin/target/kotlin-minimum-rectangle-1.0-SNAPSHOT-jar-with-dependencies.jar ./minimum-rect.jar

CMD ["java", "-cp", "minimum-rect.jar", "Stage4"]

