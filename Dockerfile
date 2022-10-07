FROM openjdk:8
ADD target/elevatormanagement-mysql.jar elevatormanagement-mysql.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "elevatormanagement-mysql.jar"]