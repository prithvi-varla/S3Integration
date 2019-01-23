package com.example.batchTest.service;


import com.example.batchTest.TaskRequest;
import com.example.batchTest.batch.configuration.EmployeeDao;
import com.example.batchTest.batch.configuration.EmployeeDaoImpl;
import com.example.batchTest.batch.configuration.JobUtility;
import com.example.batchTest.employee.Employee;

import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    JobUtility jobUtility;

    @Autowired
    EmployeeDao employeeDao;


    public void jobRun(TaskRequest request) throws Exception {

        jobUtility.executeJob(request);

    }

    public List<Employee> getEmployess() {

        return employeeDao.getEmployee();
    }



}

