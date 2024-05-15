package br.com.fiap.productmanagement.adapter.config.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
public class JobConfiguration {

  @Bean
  public Job processProduct(JobRepository jobRepository, Step step) {

    return new JobBuilder("processProduct", jobRepository)
            .incrementer(new RunIdIncrementer())
            .start(step)
            .build();

  }

  @Bean
  public JobLauncher jobLauncherAsync(JobRepository jobRepository) throws Exception {

    var jobLauncher = new TaskExecutorJobLauncher();

    jobLauncher.setJobRepository(jobRepository);
    jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
    jobLauncher.afterPropertiesSet();

    return jobLauncher;

  }

}