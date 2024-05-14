package br.com.fiap.productmanagement.domain.usecase;

import br.com.fiap.productmanagement.ports.inputport.FileInputPort;
import br.com.fiap.productmanagement.ports.outputport.JobOutputPort;
import lombok.SneakyThrows;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.SimpleAsyncTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Component
public class SchedulingJobUseCase {

  private final FileInputPort fileInputPort;
  private final JobOutputPort jobOutputPort;

  public SchedulingJobUseCase(
          FileInputPort fileInputPort,
          JobOutputPort jobOutputPort) {

    this.fileInputPort = fileInputPort;
    this.jobOutputPort = jobOutputPort;

  }

  public void start(MultipartFile file, LocalDateTime dateTime) throws Exception {

    fileInputPort.upload(file);

    PrepareDateTimeSchedulingUseCase prepareDateTimeSchedulingUseCase = new PrepareDateTimeSchedulingUseCase();
    prepareDateTimeSchedulingUseCase.setSchedulingDateTime(dateTime);

    CronTrigger trigger = new CronTrigger(prepareDateTimeSchedulingUseCase.getSchedulingDatetimeFormatted());
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