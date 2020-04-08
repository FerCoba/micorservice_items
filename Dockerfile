FROM openjdk:8
VOLUME /tmp
EXPOSE 8002
ADD ./target/microservice_item-0.0.1-SNAPSHOT.jar microservice_item.jar
ENTRYPOINT ["java","-jar","/microservice_item.jar"] 