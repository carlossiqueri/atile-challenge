# Desafio Técnico - Atile

Este projeto é uma solução para o desafio técnico proposto pela Atile, englobando um frontend desenvolvido em React.js e um backend construído com Spring Boot.

---

## Visão Geral

O projeto consiste em uma aplicação para gerenciamento de tickets, permitindo a criação, listagem, detalhamento, atualização e exclusão de tickets. O backend fornece uma API RESTful para estas operações, enquanto o frontend oferece uma interface de usuário para interagir com a API.

---

## Funcionalidades

* Criação de novos tickets.
* Listagem de todos os tickets existentes.
* Visualização detalhada de um ticket específico por ID.
* Atualização do status ou outros detalhes de um ticket.
* Exclusão de tickets.

---

## Tecnologias Utilizadas

### Frontend
* React.js
* Axios (para requisições HTTP)

### Backend
* Java 17+
* Spring Boot
* Maven (para gerenciamento de dependências e build)

---

## Pré-requisitos

Antes de começar, garanta que você tem as seguintes ferramentas instaladas em sua máquina:

* [Node.js](https://nodejs.org/) (que inclui o npm)
* [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) - Versão 17 ou superior
* [Git](https://git-scm.com/)

---

##  Configuração e Execução do Projeto

Siga os passos abaixo para configurar e rodar o projeto em seu ambiente local:

### 1. Clonar o Repositório
```bash
git clone [https://github.com/carlossiqueri/atile-challenge.git](https://github.com/carlossiqueri/atile-challenge.git)
cd atile-challenge
```

### 2. Executando o Frontend

Abra um novo terminal para o frontend.

```bash
# Navegue até a pasta do frontend
cd front-end

# Instale as dependências do projeto
npm install

# Execute o servidor de desenvolvimento do frontend
npm run dev
```

O frontend estará disponível em http://localhost:5173.

### 3. Executando o Backend (API)

Abra outro terminal para o backend.

```bash
# Navegue até a pasta da API (backend)
cd api

# Compile e empacote a aplicação Spring Boot
# No Windows, use mvnw.cmd; no Linux/macOS, use ./mvnw
./mvnw clean package
# ou no Windows:
# mvnw.cmd clean package

# Navegue até o diretório onde o JAR foi gerado
cd target

# Execute a aplicação Spring Boot
# Substitua 'NOME_DO_ARQUIVO.jar' pelo nome do arquivo JAR gerado
# (ex: atile-challenge-api-0.0.1-SNAPSHOT.jar)
java -jar NOME_DO_ARQUIVO.jar
```

A API estará disponível em http://localhost:8080. 
Você pode encontrar o nome exato do arquivo .jar gerado dentro da pasta api/target/.

Alternativamente, se estiver utilizando uma IDE como IntelliJ IDEA ou Eclipse, você pode importar o projeto da API (localizado na pasta api) como um projeto Maven e executá-lo diretamente pela IDE.

### Endpoints da API

A API fornece os seguintes endpoints para gerenciamento de tickets:

- POST	/tickets	- Cria um novo ticket.
- GET	/tickets -	Lista todos os tickets.
- GET	/tickets/{id} -	Retorna os detalhes de um ticket específico.
- PUT	/tickets/{id} -	Atualiza um ticket específico.
- DELETE	/tickets/{id} -	Remove um ticket específico.

###  Considerações e Melhorias Futuras

- Persistência de Dados: Conforme o escopo do desafio não exigia explicitamente um banco de dados, a persistência dos tickets foi implementada utilizando uma lista em memória. O próximo passo natural, dada a arquitetura do projeto, seria a introdução de uma camada de Repository e a integração com um banco de dados.
- Expandir a cobertura de testes com testes b2b para garantir maior confiabilidade e facilitar refatorações.
- Frontend: Melhorias na interface do usuário, componentização e gerenciamento de estado mais avançado.

### Autor
Carlos Siqueri.



