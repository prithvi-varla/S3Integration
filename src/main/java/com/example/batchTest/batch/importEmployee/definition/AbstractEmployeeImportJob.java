package com.example.batchTest.batch.importEmployee.definition;

import com.example.batchTest.batch.configuration.ConfigurationBatchApplication;
import com.example.batchTest.batch.importEmployee.processor.EmployeeProcessor;
import com.example.batchTest.batch.importEmployee.reader.EmployeeReader;
import com.example.batchTest.batch.importEmployee.writer.EmployeeWriter;
import com.example.batchTest.employee.Employee;
import com.example.batchTest.employee.EmployeeOutput;

import org.beanio.StreamFactory;
import org.beanio.spring.BeanIOFlatFileItemReader;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

@Import(ConfigurationBatchApplication.class)
public abstract class AbstractEmployeeImportJob {


    @Autowired
    @StepScope
    public EmployeeReader employeeReader;

    @Autowired
    @StepScope
    public EmployeeProcessor employeeProcessor;

    @Autowired
    @StepScope
    public EmployeeWriter employeeWriter;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    private String PASSED_DURING_INVOCATION = null;

    public abstract ItemReader<Employee> read(String classpath) ;

    @Bean
    public Step processFile() {

        return stepBuilderFactory.get("processFile")
                .<Employee, EmployeeOutput>chunk(1)
                //.reader(employeeReader)
                .reader(read(PASSED_DURING_INVOCATION))
                .processor(employeeProcessor)
                .writer(employeeWriter)
                .faultTolerant()
                .skip(Exception.class)
                .skipLimit(10000)
                .processorNonTransactional()
                .build();



    }

}
