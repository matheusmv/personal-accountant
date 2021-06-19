# Personal Accountant
Personal finance management application

    under development...

## Tecnologias utilizadas

- [Spring Boot](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web/2.4.5)
- [Spring Data MongoDB](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-mongodb/2.4.2)
- [Spring Mail](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-mail/2.5.1)
- [Spring Security](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security/2.5.1)

## Documentation

 - [Postman Doc](https://documenter.getpostman.com/view/9922970/TzRX7kEj)

## Using MailDev for testing

- [Docker](https://www.docker.com/get-started)
- [MailDev](https://maildev.github.io/maildev/)

       docker pull maildev/maildev
       docker run -p 80:80 -p 25:25 maildev/maildev
       
       open localhost:80 in the browser

- application.yml:

       mail:
         host: localhost
         port: 25
         username: anything
         password: anything
