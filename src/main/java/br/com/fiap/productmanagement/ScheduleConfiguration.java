package br.com.fiap.productmanagement;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScheduleConfiguration {

  @Bean("prepareDateTimeSchedulingUseCase")
  public PrepareDateTimeSchedulingUseCase prepareDateTimeSchedulingUseCase() {

    return  new PrepareDateTimeSchedulingUseCase();

  }

}