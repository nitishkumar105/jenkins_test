FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/dev-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9090

ENTRYPOINT ["java","-jar","/app/app.jar","--server.port=9090","--server.address=0.0.0.0"]
