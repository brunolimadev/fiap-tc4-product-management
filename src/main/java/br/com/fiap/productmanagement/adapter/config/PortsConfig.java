package br.com.fiap.productmanagement.adapter.config;

import br.com.fiap.productmanagement.adapter.ports.inputport.FileInputPortAdapter;
import br.com.fiap.productmanagement.adapter.ports.outputport.JobOutputPortAdapter;
import br.com.fiap.productmanagement.adapter.ports.outputport.ProductManagementOutputPortAdapter;
import br.com.fiap.productmanagement.adapter.repositories.ProductRepository;
import br.com.fiap.productmanagement.ports.inputport.FileInputPort;
import br.com.fiap.productmanagement.ports.outputport.JobOutputPort;
import br.com.fiap.productmanagement.ports.outputport.ProductManagementOutputPort;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PortsConfig {

  @Bean
  public FileInputPort fileInputPort(
          @Value("${file.upload-directory}") String uploadDirectory
  ) {

    return new FileInputPortAdapter(uploadDirectory);

  }

  @Bean
  public JobOutputPort jobOutputPort(
          @Qualifier("jobLauncherAsync") JobLauncher jobLauncher,
          Job job
  ) {

    return new JobOutputPortAdapter(
            jobLauncher,
            job
    );

  }

  @Bean
  public ProductManagementOutputPort productManagementOutputPort(ProductRepository productRepository) {

    return new ProductManagementOutputPortAdapter(productRepository);

  }


}