package com.example.batchTest.batch.importEmployee.reader;

import com.example.batchTest.employee.Employee;

import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

@Component
public class EmployeeReader implements ItemReader<Employee> {

    @Override
    public Employee read() throws Exception {
        return null;
    }
}
