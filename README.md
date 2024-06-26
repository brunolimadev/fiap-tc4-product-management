# ⭐ FIAP - Pós Tech (Fase 4) - **API de gerenciamento de produtos**

---

## 💥 Descrição:
- O projeto "Gerenciamento de produtos" foi criado como critério de avaliação na 4ª fase da pós tech FIAP.

## 🛠️ Funcionalidades:

- **Processa controle de produtos via arquivo .csv**
  - Dado uma data (ou não) para agendamento, realiza o upload do arquivo e o processa na base de dados
  

- **Lista todos os produtos**
  - Retorna todos os produtos registrados  

  
- **Retorna um produto por id**
  - Dado um código de produto retorna os seus respectivos detalhes


- **Remove um produto por id**
    - Dado um código de produto o remove da base de dados e retorna os seus respectivos detalhes


- **Atualiza um produto por id**
    - Dado um código de produto o atualiza na base de dados e retorna os seus respectivos detalhes


## 🚀 Sobre a Aplicação
- **Desenvolvida utilizando boas práticas de “Clean code”, com arquitetura hexagonal aplicada**
- **Cobertura de testes unitários**


## 🛠️ Repositório
- ####  https://github.com/brunolimadev/fiap-tc4-product-management


## 🚀 Tecnologias Utilizadas
- **Spring Boot:** versão 3.2.5
- **Java:** versão 17
- **H2 database:** versão 2.2.224
- **Spring Batch:** versão 5.1.1
- **Springdoc-openapi-Swagger:** versão 2.5.0
- **Lombok:**  versão 1.18.32
- **JUnit:** versão: 5.10.2

## 🛠️ Ferramentas Utilizadas
- [GitHub](https://github.com/)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/)
- [Postman](https://www.postman.com/)
- [Draw.io](https://app.diagrams.net/)

## 📋 Swagger
- http://localhost:8081/swagger-ui/index.html#/



## 😎 Collection e arquivo .csv para testes
- [fiap-product-manager.postman_collection.zip](https://github.com/brunolimadev/fiap-tc4-product-management/blob/develop/api-test-files/fiap-product-manager.postman_collection.zip?raw=true)
  - **Obs.:** para utilizar a coleção primeiro é preciso descompactar o arquivo e importá-lo no Postman
- [example-file-control-products.zip](https://github.com/brunolimadev/fiap-tc4-product-management/blob/develop/api-test-files/example-file-control-products.zip?raw=true)
  - **Obs.:** para realizar o processamento do arquivo de exemplo ele deve ser descompactado antes de realizar a chamada


## ⭐ Squad
- **Grupo 57:**
  - Bruno Rafael de Lima da Rocha
  - Eric Leonardo Santos Rangel
  - Wiliam Nascimento da Silva
  - Lucas Aparecido da Silva Mantovani



