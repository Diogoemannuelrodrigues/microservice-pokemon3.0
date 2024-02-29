FROM openjdk:11-jre

WORKDIR /app

COPY target/eureka-server-0.0.1-SNAPSHOT.jar /app/eureka.jar

EXPOSE 8761

CMD ["java","-jar", "eureka.jar"]

#lembra de colocar na rede --network#