package com.example.batchTest.controller;

import com.example.batchTest.TaskRequest;
import com.example.batchTest.employee.Employee;
import com.example.batchTest.service.EmployeeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
public class EmployeeController {


    @GetMapping(path = "/employeeImport/v1/getEmployee")
    public ResponseEntity<Employee> getEmployee() {

        EmployeeService service = new EmployeeService();


        return new ResponseEntity<Employee>(HttpStatus.OK);

    }

    @PostMapping(path = "/employeeImport/v1/employee")
    public ResponseEntity<String> postEmployee(@RequestBody TaskRequest request) throws Exception {

        EmployeeService service = new EmployeeService();

        request.setJobPathUrl("\\employeeText.txt");
        request.setTaskDefinitionName("EmployeeImport");

        service.jobRun(request);

        return ResponseEntity.ok("Success");
    }
}
