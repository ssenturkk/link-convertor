FROM amazoncorretto:11-alpine-jdk
MAINTAINER ssenturk
COPY target/trendyol-link-convertor-0.0.1-SNAPSHOT.jar trendyol-link-convertor.jar
ENTRYPOINT ["java","-jar","/trendyol-link-convertor.jar"]