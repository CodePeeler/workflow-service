# Messaging with Spring AMQP (RabbitMQ)

## Details
Initial rough draft of a Workflow Orchestrator. A Spring Boot service that leverages Spring's RabbitMQ library for messaging. The application includes both Producer and Consumer. The message broker is not included. However, details for spining-up a RabbitMQ docker image are given below. Plese see the [documentation](./docs) for further details about this serverice.


## Build source code
```bash
mvn clean package spring-boot
```

## Start service
#### *Navigate to project root via command line and execute.*
```bash
mvn spring-boot:run
```
___

## Optional 
#### Use RabbitMQ docker image
```bash
 docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.10-management
```

#### *Management console is avaliable at; (login with guest/guest)*
```
http://localhost:15672/
```
