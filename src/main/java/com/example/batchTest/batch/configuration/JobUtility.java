package com.example.batchTest.batch.configuration;


import com.example.batchTest.TaskRequest;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class JobUtility {

    @Autowired
    public JobRegistry jobRegistry;

    @Autowired
    @Qualifier("asyncJobLauncher")
    public JobLauncher jobLauncher;

    public void executeJob(TaskRequest request) throws Exception{

        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder()
                .addString("jobPathUrl", request.getJobPathUrl())
                .addString("taskDefinitionName", request.getTaskDefinitionName())
                .addLong("time",System.currentTimeMillis());

        Job job = jobRegistry.getJob(request.getTaskDefinitionName());

        jobLauncher.run(job, jobParametersBuilder.toJobParameters());


    }
}
