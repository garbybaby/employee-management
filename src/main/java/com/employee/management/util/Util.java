package com.employee.management.util;


import com.employee.management.dto.EmployeeDto;
import com.employee.management.model.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;

@Configuration
public class Util {

    ModelMapper modelMapper = new ModelMapper();

    public Employee convertToEntity(EmployeeDto employeeDto) throws ParseException {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        return employee;
    }

    public EmployeeDto convertToDto(Employee employee) {
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        return employeeDto;
    }

}
