package br.com.fiap.productmanagement.domain.usecase.impl;

import br.com.fiap.productmanagement.domain.entities.DateTimeSchedulingEntity;
import br.com.fiap.productmanagement.domain.usecase.SchedulingJobUseCase;
import br.com.fiap.productmanagement.ports.inputport.FileInputPort;
import br.com.fiap.productmanagement.ports.outputport.JobOutputPort;
import lombok.SneakyThrows;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.SimpleAsyncTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public class SchedulingJobUseCaseImpl implements SchedulingJobUseCase {

  private final FileInputPort fileInputPort;
  private final JobOutputPort jobOutputPort;

  public SchedulingJobUseCaseImpl(
          FileInputPort fileInputPort,
          JobOutputPort jobOutputPort) {

    this.fileInputPort = fileInputPort;
    this.jobOutputPort = jobOutputPort;

  }

  @Override
  public void start(MultipartFile file, LocalDateTime dateTime) throws Exception {

    fileInputPort.upload(file);

    DateTimeSchedulingEntity dateTimeSchedulingEntity = new DateTimeSchedulingEntity();
    dateTimeSchedulingEntity.setSchedulingDateTime(dateTime);

    CronTrigger trigger = new CronTrigger(dateTimeSchedulingEntity.getSchedulingDatetimeFormatted());
    TaskScheduler taskScheduler = new SimpleAsyncTaskScheduler();

    taskScheduler.schedule(new Runnable() {
      @SneakyThrows
      @Override
      public void run() {

        System.out.println("Executando");

        jobOutputPort.run(file.getOriginalFilename(), fileInputPort.getTargetLocation(file));

      }
    }, trigger);

  }

}