package br.com.fiap.productmanagement.adapter.config.batch;

import br.com.fiap.productmanagement.domain.entities.ProductEntity;
import br.com.fiap.productmanagement.domain.usecase.impl.PrepareProductToProcessUseCaseImpl;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

@Configuration
public class ItemConfiguration {

  @Bean
  @StepScope
  public FlatFileItemReader<ProductEntity> itemReader(
          @Value("#{jobParameters['productFile']}") Resource resource
  ) {

    BeanWrapperFieldSetMapper<ProductEntity> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
    fieldSetMapper.setTargetType(ProductEntity.class);

    return  new FlatFileItemReaderBuilder<ProductEntity>()
            .name("itemReader")
            .resource(resource)
            .linesToSkip(1)
            .lineMapper(lineMapper())
            .fieldSetMapper(fieldSetMapper)
            .build();

  }

  @Bean
  public LineMapper<ProductEntity> lineMapper() {

    BeanWrapperFieldSetMapper<ProductEntity> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
    fieldSetMapper.setTargetType(ProductEntity.class);

    DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
    lineTokenizer.setDelimiter(";");
    lineTokenizer.setNames("description", "price", "storeQuantity");

    DefaultLineMapper<ProductEntity> defaultLineMapper = new DefaultLineMapper<>();
    defaultLineMapper.setLineTokenizer(lineTokenizer);
    defaultLineMapper.setFieldSetMapper(fieldSetMapper);

    return defaultLineMapper;

  }

  @Bean
  public ItemWriter<ProductEntity> itemWriter(DataSource dataSource) {

    return  new JdbcBatchItemWriterBuilder<ProductEntity>()
            .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
            .dataSource(dataSource)
            .sql("""
                    INSERT INTO products (description, price, store_quantity, create_datetime)
                    VALUES (:description, :price, :storeQuantity, :createDateTime)
                 """
            )
            .build();

  }

  @Bean
  public ItemProcessor<ProductEntity, ProductEntity> itemProcessor() {

    return new PrepareProductToProcessUseCaseImpl();

  }

}