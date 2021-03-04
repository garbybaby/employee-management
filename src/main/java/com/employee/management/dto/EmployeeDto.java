package com.employee.management.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeDto implements Serializable {

    private Integer id;
    private String firstName;
    private String lastName;
    private String address;
    private  String state;
    private  String country;
    private  String zipcode;
    private String department;
}
