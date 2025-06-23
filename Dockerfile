FROM eclipse-temurin:17-jdk-jammy AS build

WORKDIR /warehouse_test

COPY pom.xml .
COPY mvnw .
COPY .mvn/ .mvn/
COPY src/ ./src

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jre-jammy

WORKDIR /warehouse_test

RUN addgroup --system appuser  \
    && adduser --system --group appuser

USER appuser

COPY --from=build /warehouse_test/target/warehouse_test-0.0.1-SNAPSHOT.jar warehouse_test.jar

EXPOSE 8080

CMD ["java", "-jar", "warehouse_test.jar", "--spring.profiles.active=docker"]
