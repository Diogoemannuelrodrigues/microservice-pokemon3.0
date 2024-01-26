FROM openjdk:11-jre

WORKDIR /app

ARG JAR_FILE

COPY target/${JAR_FILE} /app/eureka.jar

EXPOSE 8761

CMD ["java","-jar","eureka.jar"]
