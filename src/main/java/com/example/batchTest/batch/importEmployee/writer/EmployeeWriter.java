package com.example.batchTest.batch.importEmployee.writer;

import com.example.batchTest.batch.configuration.EmployeeDao;
import com.example.batchTest.batch.configuration.EmployeeDaoImpl;
import com.example.batchTest.employee.EmployeeOutput;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

@Component
@StepScope
public class EmployeeWriter implements ItemWriter<EmployeeOutput> {

    @Autowired
    @Qualifier("initalizedBStatement")
    Statement statement;

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public void write(List<? extends EmployeeOutput> items) throws Exception {

        Statement s = statement;

        System.out.println("Inserting records into the table...");

        for (EmployeeOutput emp: items ) {

            employeeDao.insertEmployee(emp);

        }
    }
}
