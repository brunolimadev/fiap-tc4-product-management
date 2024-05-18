package br.com.fiap.productmanagement.adapter.ports.outputport;

import br.com.fiap.productmanagement.ports.exception.OutputPortException;
import br.com.fiap.productmanagement.ports.outputport.JobOutputPort;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;

import java.nio.file.Path;

import static br.com.fiap.productmanagement.utils.MessageEnumUtils.JOB_OUTPUT_PORT_EXCEPTION;

public class JobOutputPortAdapter implements JobOutputPort {

  private final JobLauncher jobLauncher;
  private final Job job;

  public JobOutputPortAdapter(
          JobLauncher jobLauncher,
          Job job
  ) {

    this.jobLauncher = jobLauncher;
    this.job = job;

  }

  @Override
  public void run(String fileName, Path targetLocation) throws OutputPortException {

    try {

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

    } catch (JobExecutionException jobExecutionException) {

      throw new OutputPortException(JOB_OUTPUT_PORT_EXCEPTION.getMessage());

    }

  }

}