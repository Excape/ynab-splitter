FROM openjdk:8-jdk-alpine
RUN addgroup -S ynabsplitter && adduser -S ynabsplitter -G ynabsplitter
USER ynabsplitter:ynabsplitter
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]