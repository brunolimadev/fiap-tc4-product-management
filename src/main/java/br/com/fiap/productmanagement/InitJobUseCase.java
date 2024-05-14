package br.com.fiap.productmanagement;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class InitJobUseCase {

  private final JobLauncher jobLauncher;
  private final Job job;

  public InitJobUseCase(
          @Qualifier("jobLauncherAsync") JobLauncher jobLauncher,
          Job job
  ) {

    this.jobLauncher = jobLauncher;
    this.job = job;

  }

  public void run(
          String fileName,
          Path targetLocation
  ) throws JobExecutionException {

    var jobParameters = new JobParametersBuilder()
            .addJobParameter(
                    "product",
                    fileName,
                    String.class,
                    true
            )
            .addJobParameter(
                    "productFile",
                    "file:" + targetLocation,
                    String.class
            )
            .toJobParameters();

    jobLauncher.run(job, jobParameters);

  }

}