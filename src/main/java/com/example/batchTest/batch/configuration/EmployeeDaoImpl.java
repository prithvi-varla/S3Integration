package com.example.batchTest.batch.configuration;

import com.example.batchTest.employee.Employee;
import com.example.batchTest.employee.EmployeeOutput;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    @Qualifier("initalizedBStatement")
    Statement statement;

    @Override
    public void insertEmployee(EmployeeOutput employee) {

        try {

            String sql = "INSERT INTO employee(firstName, lastName) VALUES('" + employee.getFirstName() + "', '" + employee.getLastName() + "')";

            statement.executeUpdate(sql);

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public List<Employee> getEmployee() {

        List<Employee> employees = new ArrayList<>();


        try {

            String sql = "SELECT firstName, lastName from employee";

            ResultSet employeeList = statement.executeQuery(sql);

            while(employeeList.next()) {

                Employee employee = new Employee();

                String first = employeeList.getString("firstName");
                String last = employeeList.getString("lastName");

                employee.setFirstName(first);
                employee.setLastName(last);


                employees.add(employee);
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return  employees;

    }

}
