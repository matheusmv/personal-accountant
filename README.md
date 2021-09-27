# Personal Accountant
Personal finance management application

    under development...

## Documentation

 - [Postman Doc](https://documenter.getpostman.com/view/9922970/TzRX7kEj)

## Setting environment variables

 - [application.yml](https://github.com/matheusmv/personal-accountant/blob/main/app/src/main/resources/application.yml)

 - [docker-compose.yml](https://github.com/matheusmv/personal-accountant/blob/main/docker-compose.yml)

## Running the application

 - [Docker](https://www.docker.com/get-started)
 - [Docker Compose](https://docs.docker.com/compose/install/)

        git clone https://github.com/matheusmv/personal-accountant.git && cd /personal-accountant
      
        docker-compose up -d && ./mvnw package && java -jar app/target/app-0.0.1.jar
