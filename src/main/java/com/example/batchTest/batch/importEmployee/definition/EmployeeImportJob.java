package com.example.batchTest.batch.importEmployee.definition;


import com.example.batchTest.employee.Employee;

import org.beanio.StreamFactory;
import org.beanio.spring.BeanIOFlatFileItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
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



    @Bean(name = "employeeReadJob")
    @StepScope
    public BeanIOFlatFileItemReader<Employee> read(@Value("#{jobParameters[jobPathUrl]}") String pathToFile) {


        BeanIOFlatFileItemReader<Employee> reader = null;

        try {

            String filePath = pathToFile;

            reader = new BeanIOFlatFileItemReader<Employee>();
            reader.setStreamFactory(StreamFactory.newInstance());
            reader.setResource(new FileSystemResource(filePath));
            reader.setStreamMapping(new ClassPathResource("employee-mapping.xml"));
            reader.setStreamName("employees");
            reader.afterPropertiesSet();
            reader.open(new ExecutionContext());

        } catch (Exception e) {
            System.out.println(e);
        }

        return reader;

    }


}
