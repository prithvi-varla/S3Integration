package com.example.batchTest.batch.importEmployee.writer;

import com.example.batchTest.employee.EmployeeOutput;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeWriter implements ItemWriter<EmployeeOutput> {

    @Override
    public void write(List<? extends EmployeeOutput> items) throws Exception {

    }
}
