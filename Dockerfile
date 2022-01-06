FROM openjdk:8-jdk-alpine
EXPOSE 8083
ADD target/timesheet-1.0.war timesheet-1.0.war
ENTRYPOINT ["java","-jar","/timesheet-1.0.war"]
