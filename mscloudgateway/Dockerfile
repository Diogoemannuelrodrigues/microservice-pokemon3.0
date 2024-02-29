FROM openjdk:11-jre

WORKDIR /app

COPY target/mscloudgateway-0.0.1-SNAPSHOT.jar  /app/gateway.jar

EXPOSE 8761

CMD ["java","-jar", "gateway.jar"]