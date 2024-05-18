package br.com.fiap.productmanagement.adapter.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {

    Contact contact = new Contact();
    contact.setName("Turma: 2ADJT - Grupo 57 - Repositório do projeto");
    contact.setUrl("https://github.com/brunolimadev/fiap-tc4-product-management");

    Info info = new Info()
            .title("Product Management API")
            .contact(contact)
            .version("1.0")
            .description("Tech Challenge - Fase 4");

    return new OpenAPI()
            .info(info);
  }

}