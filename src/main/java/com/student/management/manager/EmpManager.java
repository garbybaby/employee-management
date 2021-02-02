package com.student.management.manager;

import com.student.management.model.Employee;

import java.util.List;

public interface EmpManager {

    List<Employee> getAllEmployees() throws Exception;
    Employee getEmployeeById(Integer id) throws Exception;
    Employee createOrUpdateEmployee(Employee employee) throws Exception;
    void deleteEmployeeById(Integer employee) throws Exception;
}
