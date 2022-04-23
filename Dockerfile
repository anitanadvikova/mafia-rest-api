FROM openjdk:11-jre

WORKDIR /opt/anita

ARG JVM_OPTS="-XX:+PrintFlagsFinal -Xms256m -Xmx512m -XX:MaxRAM=640m"

COPY ./target/anita-1.0-SNAPSHOT.jar anita.jar

EXPOSE 8086

CMD ["-jar" , "/opt/anita/anita.jar"]

ENTRYPOINT [ "java", "-Dspring.profiles.active=docker" ]