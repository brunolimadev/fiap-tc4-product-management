package br.com.fiap.productmanagement.adapter.config.batch;

import br.com.fiap.productmanagement.domain.entities.ProductEntity;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class StepConfiguration {

  @Bean
  public Step step(
          JobRepository jobRepository,
          PlatformTransactionManager platformTransactionManager,
          ItemReader<ProductEntity> itemReader,
          ItemProcessor<ProductEntity, ProductEntity> itemProcessor,
          ItemWriter<ProductEntity> itemWriter
  ) {

    return new StepBuilder("step", jobRepository)
            .<ProductEntity, ProductEntity>chunk(20, platformTransactionManager)
            .reader(itemReader)
            .processor(itemProcessor)
            .writer(itemWriter)
            .build();

  }

}