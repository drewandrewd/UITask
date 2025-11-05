FROM maven:3.9.9-eclipse-temurin-17
WORKDIR /app
COPY pom.xml ./
RUN mvn -B -q -U -DskipTests package \
    -Dmaven.wagon.http.retryHandler.count=10 \
    -Dmaven.wagon.http.pool=false \
    -Dhttp.keepAlive=false \
    -Dmaven.artifact.threads=8
COPY . .
CMD ["mvn", "clean", "test", "-B", "-Dallure.results.directory=target/allure-results"]
