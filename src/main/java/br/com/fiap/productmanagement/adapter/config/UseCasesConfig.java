package br.com.fiap.productmanagement.adapter.config;

import br.com.fiap.productmanagement.domain.usecase.PrepareProductToProcessUseCase;
import br.com.fiap.productmanagement.domain.usecase.SchedulingJobUseCase;
import br.com.fiap.productmanagement.domain.usecase.impl.PrepareProductToProcessUseCaseImpl;
import br.com.fiap.productmanagement.domain.usecase.impl.SchedulingJobUseCaseImpl;
import br.com.fiap.productmanagement.ports.inputport.FileInputPort;
import br.com.fiap.productmanagement.ports.outputport.JobOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

  @Bean
  public SchedulingJobUseCase schedulingJobUseCase(
          FileInputPort fileInputPort,
          JobOutputPort jobOutputPort
  ) {

    return new SchedulingJobUseCaseImpl(
            fileInputPort,
            jobOutputPort
    );

  }

  @Bean
  public PrepareProductToProcessUseCase prepareProductToProcessUseCase() {

    return new PrepareProductToProcessUseCaseImpl();

  }

}