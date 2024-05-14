package br.com.fiap.productmanagement.adapter.config;

import br.com.fiap.productmanagement.domain.usecase.PrepareDateTimeSchedulingUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScheduleConfiguration {

  @Bean("prepareDateTimeSchedulingUseCase")
  public PrepareDateTimeSchedulingUseCase prepareDateTimeSchedulingUseCase() {

    return  new PrepareDateTimeSchedulingUseCase();

  }

}