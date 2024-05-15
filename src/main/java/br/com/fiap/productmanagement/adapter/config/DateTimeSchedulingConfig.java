package br.com.fiap.productmanagement.adapter.config;

import br.com.fiap.productmanagement.domain.entities.DateTimeSchedulingEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DateTimeSchedulingConfig {

  @Bean("dateTimeSchedulingEntity")
  public DateTimeSchedulingEntity dateTimeSchedulingEntity() {

    return  new DateTimeSchedulingEntity();

  }

}