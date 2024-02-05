FROM openjdk:17-jdk-slim
# Copy the JAR from target folder to the root folder of docker
COPY target/Librarian-2.0.0.jar Librarian-2.0.0.jar
# The below command is similar to  java -jar target/Librarian-2.0.0.jar
# The target/ is not needed since the JAR is available in the root folder
ENTRYPOINT ["java","-jar","Librarian-2.0.0.jar"]
# So, the entry point is nothing but the command where the
# words separated by spaces are provided under double quotes