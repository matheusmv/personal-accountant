# Personal Accountant
Personal finance management application

    under development...

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
