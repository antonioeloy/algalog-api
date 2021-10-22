<h1 align="center">
  <img alt="banner" title="banner" src="banner.png" />
</h1>

# :computer: algalog-api
API REST com Spring Boot para um sistema de entrega de mercadorias.

Este projeto foi desenvolvido durante o Mergulho Spring REST da AlgaWorks.

### :bookmark_tabs: Tabela de conteúdos
* [Status do projeto](#status)
* [Tecnologias](#tecnologias)
* [Features](#features)
* [Pré-requisitos](#requisitos)
* [Executando a aplicação](#executando)
* [Testando a aplicação](#testando)
* [Licença](#licenca)

<a name="status"/></a>
### :hourglass: Status do projeto
Concluído.

<a name="tecnologias"/></a>
### :hammer_and_wrench: Tecnologias

As seguintes tecnologias e ferramentas foram utilizadas no desenvolvimento deste projeto:

- [Java](https://www.oracle.com/java/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Boot Starter Web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web)
- [Spring Boot Starter Validation](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation)
- [Spring Boot Starter Data JPA](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa)
- [Spring Boot DevTools](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-devtools)
- [Springfox Swagger](http://springfox.github.io/springfox/)
- [Flyway](https://flywaydb.org/)
- [Lombok](https://projectlombok.org/)
- [ModelMapper](http://modelmapper.org/)
- [Maven](https://maven.apache.org/)
- [MySQL](https://www.mysql.com/)
- [Git](https://git-scm.com/)
- [Docker](http://modelmapper.org/)

<a name="features"/></a>
### :page_with_curl: Features
- [x] Listar clientes
- [x] Buscar um cliente
- [x] Cadastrar um cliente
- [x] Editar um cliente
- [x] Excluir um cliente
- [x] Listar entregas
- [x] Buscar uma entrega 
- [x] Solicitar uma entrega    
- [x] Finalizar uma entrega
- [x] Listar ocorrências de uma entrega
- [x] Registrar uma ocorrência para uma entrega

<a name="requisitos"/></a>
### :pencil: Pré-requisitos

Antes de começar, você precisa ter instalado em sua máquina as seguintes ferramentas:
- [Git](https://git-scm.com/)
- [Docker](https://www.docker.com/)

<a name="executando"/></a>
### :rocket: Executando a aplicação

```bash
# Clone este repositório
$ git clone https://github.com/antonioeloy/algalog-api.git

# Na pasta raiz do projeto, execute os contêineres da aplicação
$ docker-compose up

# A aplicação iniciará na porta 8080
```

<a name="testando"/></a>
### :gear: Testando a aplicação

Para testar a API, basta acessar a documentação gerada com a biblioteca [Springfox Swagger](http://springfox.github.io/springfox/).

```
http://localhost:8080/swagger-ui.html
```

<h1 align="center">
  <img alt="documentacao" title="documentacao" src="documentacao_api.PNG" />
</h1>

<a name="licenca"/></a>
### :copyright: Licença

Este projeto está licenciado nos termos da licença MIT.






