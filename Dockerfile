FROM openjdk:17-alpine

RUN apk update && apk add --no-cache tesseract-ocr

RUN mkdir -p /tessdata
COPY tessdata/* /tessdata

ENV TESSDATA_PREFIX=/tessdata

RUN tesseract --list-langs && tesseract -v
EXPOSE 8080

COPY target/app-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

