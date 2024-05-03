package br.com.fiap.productmanagement;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CronConfiguration {

  @Bean("cronTest")
  public CronTest cronTest() {

    return  new CronTest();

  }

}