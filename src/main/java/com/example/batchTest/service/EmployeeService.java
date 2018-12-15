package com.example.batchTest.service;


import com.example.batchTest.TaskRequest;
import com.example.batchTest.batch.configuration.JobUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    JobUtility jobUtility;


    public void jobRun(TaskRequest request) throws Exception {




        jobUtility.executeJob(request);




    }








}

