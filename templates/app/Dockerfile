FROM amazoncorretto:11 as builder
RUN mkdir -p /opt/app
WORKDIR /opt/app
COPY build.gradle.kts .
COPY gradle.properties .
COPY gradle gradle/
COPY gradlew .
COPY src src/
RUN ./gradlew bootJar

FROM amazoncorretto:11 as app
RUN yum update -y --security && yum install -y nc && yum clean all
USER 1000
WORKDIR /opt/app
COPY --from=builder /opt/app/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
