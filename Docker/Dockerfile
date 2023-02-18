FROM openjdk:17-oracle
EXPOSE 8080
ADD target/myDockerApp.jar myDockerApp.jar
ENTRYPOINT ["java","-jar","myDockerApp.jar"]