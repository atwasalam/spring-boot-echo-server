FROM openjdk:8-jdk-alpine
ARG app_version
ENV APP_VERSION=${app_version}
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
WORKDIR app
COPY lib/spring-echo-server-${APP_VERSION}.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]