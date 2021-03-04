package com.employee.management.manager;

import com.employee.management.dto.EmployeeDto;
import com.employee.management.model.Employee;

import java.util.List;

public interface EmpManager {

    List<Employee> getAllEmployees() throws Exception;
    EmployeeDto getEmployeeById(Integer id) throws Exception;
    EmployeeDto createOrUpdateEmployee(EmployeeDto employee) throws Exception;
    Integer createBulkEmployee() throws Exception;
    void deleteEmployeeById(Integer employee) throws Exception;
}
