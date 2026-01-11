# ---------- build stage ----------
FROM maven:3.9-eclipse-temurin-25 AS build
WORKDIR /workspace

# cache dependencies
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline

# build application
COPY src ./src
RUN mvn -q -DskipTests package

# ---------- runtime stage ----------
FROM eclipse-temurin:25-jre
WORKDIR /app

# run as non-root (best practice)
RUN useradd -r -u 10001 appuser
USER appuser

# copy jar
COPY --from=build /workspace/target/*.jar /app/app.jar

EXPOSE 8080
ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
