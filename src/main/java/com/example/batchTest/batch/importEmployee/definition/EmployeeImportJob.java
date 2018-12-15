package com.example.batchTest.batch.importEmployee.definition;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class EmployeeImportJob extends AbstractEmployeeImportJob {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Bean(name= "EmployeeImport")
    public Job importEmployeeJob() {

        return jobBuilderFactory.get("importEmployeeJob")
                .incrementer(new RunIdIncrementer())
                .start(processFile())
                .build();

    }


}
