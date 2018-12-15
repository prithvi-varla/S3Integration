package com.example.batchTest.batch.configuration;


import com.example.batchTest.TaskRequest;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class JobUtility {

    @Autowired
    public JobRegistry jobRegistry;

    @Autowired
    public JobLauncher jobLauncher;

    public void executeJob(TaskRequest request) throws Exception{

        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder()
                .addString("jobPathUrl", request.getJobPathUrl())
                .addString("taskDefinitionName", request.getTaskDefinitionName());

        Job job = jobRegistry.getJob(request.getTaskDefinitionName());

        jobLauncher.run(job, jobParametersBuilder.toJobParameters());


    }
}
