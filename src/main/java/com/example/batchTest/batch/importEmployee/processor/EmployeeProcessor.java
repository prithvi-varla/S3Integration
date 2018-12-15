package com.example.batchTest.batch.importEmployee.processor;

import com.example.batchTest.employee.Employee;
import com.example.batchTest.employee.EmployeeOutput;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class EmployeeProcessor implements ItemProcessor<Employee, EmployeeOutput> {

    @Override
    public EmployeeOutput process(Employee item) throws Exception {
        return null;
    }
}
