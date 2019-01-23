package com.example.batchTest.controller;

import com.example.batchTest.TaskRequest;
import com.example.batchTest.employee.Employee;
import com.example.batchTest.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping(path = "/employeeImport/v1/getEmployee")
    public ResponseEntity<List<Employee>> getEmployee() {

        List<Employee> result = employeeService.getEmployess();

        return new ResponseEntity<List<Employee>>(result,HttpStatus.OK);

    }

    @PostMapping(path = "/employeeImport/v1/employee")
    public ResponseEntity<String> postEmployeeInfo(@RequestBody TaskRequest request) throws Exception {

        request.setJobPathUrl("employeeText.txt");
        request.setTaskDefinitionName("importEmployeeJob");

        employeeService.jobRun(request);

        return ResponseEntity.ok("Success");
    }
}
