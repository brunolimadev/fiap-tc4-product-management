package br.com.fiap.productmanagement;

import lombok.SneakyThrows;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.SimpleAsyncTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Component
public class SchedulingJobUseCase {

  private final ManagementFileUseCase managementFileUseCase;
  private final InitJobUseCase initJobUseCase;

  public SchedulingJobUseCase(
          ManagementFileUseCase managementFileUseCase,
          InitJobUseCase initJobUseCase) {

    this.managementFileUseCase = managementFileUseCase;
    this.initJobUseCase = initJobUseCase;

  }

  public void start(MultipartFile file, LocalDateTime dateTime) throws Exception {

    managementFileUseCase.upload(file);

    PrepareDateTimeSchedulingUseCase prepareDateTimeSchedulingUseCase = new PrepareDateTimeSchedulingUseCase();
    prepareDateTimeSchedulingUseCase.setSchedulingDateTime(dateTime);

    CronTrigger trigger = new CronTrigger(prepareDateTimeSchedulingUseCase.getSchedulingDatetimeFormatted());
    TaskScheduler taskScheduler = new SimpleAsyncTaskScheduler();

    taskScheduler.schedule(new Runnable() {
      @SneakyThrows
      @Override
      public void run() {

        System.out.println("Executando");

        initJobUseCase.run(file.getOriginalFilename(), managementFileUseCase.getTargetLocation(file));

      }
    }, trigger);

  }

}