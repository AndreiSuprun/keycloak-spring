FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY keycloak-spring-1.0.jar keycloak-spring-1.0.jar
ENTRYPOINT ["java","-jar", "keycloak-spring-1.0.jar"]
