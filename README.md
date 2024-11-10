# Projeto de Estudo: API RESTful de Produtos com Spring Boot

Este repositório contém um projeto de estudo focado em Spring Boot, onde é criada uma API RESTful para gerenciar produtos. O projeto usa PostgreSQL como banco de dados e é configurado com Maven para gerenciamento de dependências. Postman é utilizado para testar as requisições HTTP.

### Projeto desenvolvido na linguagem Java por:
- [Felipe Fonseca](https://github.com/lipefnsc)

## Descrição do Projeto

O objetivo deste projeto é estudar os conceitos do Spring Boot na criação de uma API RESTful para gerenciamento de produtos, utilizando PostgreSQL como banco de dados. A aplicação permite realizar operações CRUD (Create, Read, Update e Delete) nos produtos.O objetivo deste projeto é construir uma aplicação Java com um conjunto de testes unitários abrangente, aplicando conceitos como cobertura de testes, boas práticas de testes e as funcionalidades do JUnit. O projeto está estruturado com Maven para facilitar o gerenciamento das dependências.

## Tecnologias Utilizadas

- Java (versão 17+)
- Spring Boot (versão 3+)
- Maven
- PostgreSQL
- Postman

## Funcionalidades
- Criar um novo produto
- Listar todos os produtos
- Buscar um produto por ID
- Atualizar informações de um produto
- Deletar um produto

## Endpoints da API

| Método  | Endpoint           | Descrição                     |
|---------|--------------------|-------------------------------|
| POST    | `/products`        | Cria um novo produto          |
| GET ALL | `/products`        | Lista todos os produtos       |
| GET ONE | `/products/{id}`   | Retorna um produto pelo ID    |
| UPDATE  | `/products/{id}`   | Atualiza um produto           |
| DELETE  | `/products/{id}`   | Deleta um produto             |

## Exemplo de JSON para criar um produto

```json
{
  "name": "Example product",
  "value": 29.99
}
```

## Uso

Para testar a aplicação, siga os passos:

1. Abra o Postman.
2. Use os endpoints descritos acima para enviar requisições HTTP para a API.
3. Acompanhe os resultados e verifique os dados no banco de dados PostgreSQL.

## Contato

[Felipe Fonseca - devfelipefonseca@outlook.com](mailto:devfelipefonseca@outlook.com)