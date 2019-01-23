package com.example.batchTest.batch.configuration;

import com.example.batchTest.employee.Employee;
import com.example.batchTest.employee.EmployeeOutput;

import java.util.List;

public interface EmployeeDao {

    public void insertEmployee(EmployeeOutput employee);

    public List<Employee> getEmployee();
}
