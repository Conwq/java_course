FROM maven:3.9.3-amazoncorretto-20-debian-bookworm as maven
WORKDIR /app
COPY . .
RUN mvn clean package

FROM tomcat:10.1.8-jdk17
COPY --from=maven /app/target/web-project.war /usr/local/tomcat/webapps/
EXPOSE 8080
