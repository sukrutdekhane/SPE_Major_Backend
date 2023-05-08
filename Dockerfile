FROM openjdk
COPY ./target/SPE_Major_project-0.0.1-SNAPSHOT.jar ./
CMD ["java","-jar","SPE_Major_project-0.0.1-SNAPSHOT.jar"]