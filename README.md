# Personal Accountant
Aplicação para gestão de finanças pessoais

    Em desenvolvimento...

## Using MailDev for testing

 - [Docker](https://www.docker.com/get-started)
 - [MailDev](https://maildev.github.io/maildev/)

    
    1. docker pull maildev/maildev
    2. docker run -p 80:80 -p 25:25 maildev/maildev
    3. open localhost:80 in the browser

 - application.yml:


    mail:
      host: localhost
      port: 25
      username: anything
      password: anything
