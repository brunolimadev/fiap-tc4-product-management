package br.com.fiap.productmanagement;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.time.LocalDateTime;

@Configuration
public class BatchConfiguration {

  @Bean
  public Job processProduct(JobRepository jobRepository, Step step) {

    return new JobBuilder("processProduct", jobRepository)
            .incrementer(new RunIdIncrementer())
            .start(step)
            .build();

  }

  @Bean
  public Step step(
          JobRepository jobRepository,
          PlatformTransactionManager platformTransactionManager,
          ItemReader<Product> itemReader,
          ItemProcessor<Product, Product> itemProcessor,
          ItemWriter<Product> itemWriter
  ) {

    return new StepBuilder("step", jobRepository)
            .<Product, Product>chunk(20, platformTransactionManager)
            .reader(itemReader)
            .processor(itemProcessor)
            .writer(itemWriter)
            .build();

  }

  @Bean
  public ItemReader<Product> itemReader() {
    System.out.println(LocalDateTime.now());
    BeanWrapperFieldSetMapper<Product> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
    fieldSetMapper.setTargetType(Product.class);

    return  new FlatFileItemReaderBuilder<Product>()
            .name("productItemReader")
            .resource(new ClassPathResource("products-tech-challenge-4.csv"))
            .linesToSkip(1)
            .lineMapper(lineMapper())
            .fieldSetMapper(fieldSetMapper)
            .build();

  }

  @Bean
  public LineMapper<Product> lineMapper() {

    BeanWrapperFieldSetMapper<Product> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
    fieldSetMapper.setTargetType(Product.class);

    DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
    lineTokenizer.setDelimiter(";");
    lineTokenizer.setNames("description", "price", "storeQuantity");

    DefaultLineMapper<Product> defaultLineMapper = new DefaultLineMapper<>();
    defaultLineMapper.setLineTokenizer(lineTokenizer);
    defaultLineMapper.setFieldSetMapper(fieldSetMapper);

    return defaultLineMapper;
  }

  @Bean
  public ItemWriter<Product> itemWriter(DataSource dataSource) {

    return  new JdbcBatchItemWriterBuilder<Product>()
            .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
            .dataSource(dataSource)
            .sql("""
                    INSERT INTO products (description, price, storeQuantity, createDateTime)
                    VALUES (:description, :price, :storeQuantity, :createDateTime)
                 """
            )
            .build();

  }

  @Bean
  public ItemProcessor<Product, Product> itemProcessor() {

    return new ProductProcessor();

  }

}