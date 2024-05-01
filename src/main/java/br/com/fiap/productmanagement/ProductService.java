package br.com.fiap.productmanagement;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ProductService {

  private final Path filePath;
  private final JobLauncher jobLauncher;
  private final Job job;

  public ProductService(
          @Value("${file.upload-directory}") String uploadDirectory,
          @Qualifier("jobLauncherAsync") JobLauncher jobLauncher,
          Job job
  ) {

    this.filePath = Paths.get(uploadDirectory);
    this.jobLauncher = jobLauncher;
    this.job = job;

  }

  public void uploadFile(MultipartFile file) throws Exception {

    var fileName = StringUtils.cleanPath(file.getOriginalFilename());
    var targetLocation = filePath.resolve(fileName);

    file.transferTo(targetLocation);

    var jobParameters = new JobParametersBuilder()
            .addJobParameter(
                    "product",
                    file.getOriginalFilename(),
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