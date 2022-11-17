### How to run backend app

## Build database
docker run -e POSTGRES_PASSWORD=postgrespw -p 49153:5432/tcp postgres

## Run application using java 17
java -jar backend-0.0.1-SNAPSHOT.jar
