package com.example.batchTest.batch.importEmployee.reader;

import com.example.batchTest.employee.Employee;

import org.beanio.StreamFactory;
import org.beanio.spring.BeanIOFlatFileItemReader;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import javax.batch.runtime.context.JobContext;
import org.springframework.beans.factory.annotation.Value;
import io.swagger.annotations.Scope;

@Component
@StepScope
public class EmployeeReader implements ItemReader<Employee> {



    @Value("#{jobParameters[jobPathUrl]}")
    public String pathToFile;

    @Override
    public Employee read() {

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

            Employee test = reader.read();
            Employee test2 = reader.read();
            return test;



        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
