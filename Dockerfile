# base docker image
FROM openjdk:11
LABEL maintainer="dynatrace.dudyshev"
ADD target/dynatrace-0.0.1-SNAPSHOT.jar dynatrace-dudyshev.jar
ENTRYPOINT ["java", "-jar", "dynatrace-dudyshev.jar"]
