FROM adoptopenjdk/openjdk11:alpine-slim
RUN addgroup -S ynabsplitter && adduser -S ynabsplitter -G ynabsplitter
USER ynabsplitter:ynabsplitter
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]