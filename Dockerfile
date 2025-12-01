FROM amazoncorretto:17.0.12

WORKDIR /app
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} tiny-ledger.jar

ENTRYPOINT ["java","-jar","tiny-ledger.jar"]