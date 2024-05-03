package br.com.fiap.productmanagement;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ProductSchedule {

  @Scheduled(cron = "#{cronTest.test()}", zone = "America/Sao_Paulo")
  public void uploadFileCron() {

    System.out.println("Executando...");

  }

}