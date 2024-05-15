package br.com.fiap.productmanagement.domain.usecase.impl;

import br.com.fiap.productmanagement.domain.entities.DateTimeSchedulingEntity;
import br.com.fiap.productmanagement.domain.exception.EntityException;
import br.com.fiap.productmanagement.domain.exception.UseCaseException;
import br.com.fiap.productmanagement.domain.usecase.SchedulingJobUseCase;
import br.com.fiap.productmanagement.ports.exception.InputPortException;
import br.com.fiap.productmanagement.ports.exception.OutputPortException;
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
  public void start(MultipartFile file, LocalDateTime dateTime) throws UseCaseException {

    try {

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

    } catch (InputPortException | EntityException | OutputPortException exception) {

      throw exception;

    } catch (Exception exception) {

      throw new UseCaseException("Ocorreu um erro ao tentar realizar o agendamento de inicialização do job");

    }

  }

}