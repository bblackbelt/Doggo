FROM gradle:6.6.1-jdk14 as builder

# Copy local code to the container image.
COPY build.gradle .
COPY gradle.properties .
COPY settings.gradle .
COPY src ./src
COPY resources ./resources

# Build a release artifact.
RUN gradle clean build --no-daemon

FROM adoptopenjdk/openjdk14:alpine-slim

# Copy the jar to the production image from the builder stage.
COPY --from=builder /home/gradle/build/libs/doggo-0.0.1-all.jar /doggo-0.0.1.jar

# Run the web service on container startup.
CMD ["java", "-server", "-jar", "doggo-0.0.1.jar"]