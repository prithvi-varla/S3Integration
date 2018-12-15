package com.example.batchTest.batch.importEmployee.definition;

import com.example.batchTest.batch.configuration.ConfigurationBatchApplication;
import com.example.batchTest.batch.importEmployee.processor.EmployeeProcessor;
import com.example.batchTest.batch.importEmployee.reader.EmployeeReader;
import com.example.batchTest.batch.importEmployee.writer.EmployeeWriter;
import com.example.batchTest.employee.Employee;
import com.example.batchTest.employee.EmployeeOutput;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

//@Import(ConfigurationBatchApplication.class)
public abstract class AbstractEmployeeImportJob {

    @Autowired
    public EmployeeReader employeeReader;

    @Autowired
    public EmployeeProcessor employeeProcessor;

    @Autowired
    public EmployeeWriter employeeWriter;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    public Step processFile() {

        return stepBuilderFactory.get("processFile")
                .<Employee, EmployeeOutput>chunk(2)
                .reader(employeeReader)
                .processor(employeeProcessor)
                .writer(employeeWriter)
                .faultTolerant()
                .skip(Exception.class)
                .skipLimit(10000)
                .processorNonTransactional()
                .build();



    }

}
