package com.employee.management.manager;

import com.employee.management.model.Employee;

import java.util.List;

public interface EmpPageManager {

    public List<Employee> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy);
}
