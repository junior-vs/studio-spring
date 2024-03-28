package com.studio.spring.services;

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
import java.util.Objects;

@Service
public class CnabService {

    private final Path fileStorageLocation;
    private final Job job;
    private final JobLauncher jobLauncher;

    public CnabService(@Value("${file.upload-dir}") String fileStorageLocation,
                       Job job,
                       @Qualifier("jobLauncherAsync") JobLauncher jobLauncher) {
        this.fileStorageLocation = Paths.get(fileStorageLocation);
        this.job = job;
        this.jobLauncher = jobLauncher;
    }


    @SuppressWarnings("null")
    public void upload(MultipartFile file) throws Exception {

        var fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        var targetLocation = fileStorageLocation.resolve(fileName);
        file.transferTo(targetLocation);

        var jobParameters = new JobParametersBuilder()
                .addJobParameter("cnab", file.getOriginalFilename(), String.class, true)
                .addJobParameter("cnabFile", "file:" + targetLocation, String.class, false)
                .toJobParameters();


        jobLauncher.run(job, jobParameters);


    }
}
