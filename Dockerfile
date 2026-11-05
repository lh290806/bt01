# Stage 1: Build application with Maven
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom.xml and source code
COPY pom.xml .
COPY src ./src
COPY web ./web

# Package ROOT.war
RUN mvn clean package -DskipTests

# Stage 2: Run Tomcat 10.1 (Jakarta EE 10 / Servlet 6.0)
FROM tomcat:10.1-jre21-temurin

# Remove default ROOT application to deploy as ROOT context
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy generated ROOT.war into Tomcat webapps
COPY --from=build /app/target/ROOT.war /usr/local/tomcat/webapps/ROOT.war

# Expose default port
EXPOSE 8080

# Dynamically bind to Render's assigned PORT (defaults to 8080 if not specified)
CMD sed -i "s/port=\"8080\"/port=\"${PORT:-8080}\"/g" /usr/local/tomcat/conf/server.xml && catalina.sh run
