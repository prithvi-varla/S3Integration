package com.example.batchTest.batch.importEmployee.processor;

import com.example.batchTest.employee.Employee;
import com.example.batchTest.employee.EmployeeOutput;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class EmployeeProcessor implements ItemProcessor<Employee, EmployeeOutput> {

    @Override
    public EmployeeOutput process(Employee item) throws Exception {

        // Process the input employee item and then orchestrate to produce employeeOutput file
        EmployeeOutput output = new EmployeeOutput();
        output.setFirstName(item.getFirstName());
        output.setLastName(item.getLastName());

        return output;
    }
}
