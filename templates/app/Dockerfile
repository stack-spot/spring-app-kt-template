FROM amazoncorretto:11 as builder
RUN mkdir -p /opt/app
WORKDIR /opt/app
COPY gradle gradle/
COPY gradlew .
RUN ./gradlew > /dev/null
COPY *.kts ./
COPY gradle.properties .
RUN ./gradlew dependencies > /dev/null
COPY src src/
RUN ./gradlew bootJar

FROM amazoncorretto:11 as app
RUN yum update -y --security && yum install -y nc && yum clean all
ARG USER=1000
USER $USER
WORKDIR /opt/app
COPY --from=builder /opt/app/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
