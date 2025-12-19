FROM maven:3.8-openjdk-17 AS build

# Copy source code
COPY . /app
WORKDIR /app

# Build the application
RUN mvn clean package

# Production stage
FROM tomcat:9.0-jdk17-openjdk

# Remove default webapps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the WAR file from build stage
COPY --from=build /app/target/todo-webapp.war /usr/local/tomcat/webapps/ROOT.war

# Expose port 8080
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]