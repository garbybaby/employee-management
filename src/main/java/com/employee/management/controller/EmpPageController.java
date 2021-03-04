package com.employee.management.controller;


import com.employee.management.manager.EmpPageManager;
import com.employee.management.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmpPageController {
    @Autowired
    EmpPageManager service;
    private static final Logger logger = LogManager.getLogger(EmpPageController.class);

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(
            @PathParam("pageNo") Integer pageNo,
            @PathParam("pageSize") Integer pageSize,
            @PathParam("sortBy") String sortBy) {
        List<Employee> list = service.getAllEmployees(pageNo, pageSize, sortBy);
        logger.info("Returning " + list.size() + " number of employees");
        logger.info("pageNo " + pageNo + "| pageSize : " + pageSize + " sortBy : " + sortBy);

        for (Employee employee : list
        ) {
            logger.info("Emp rtn " + employee.toString());
        }
        return new ResponseEntity<List<Employee>>(list, new HttpHeaders(), HttpStatus.OK);
    }
}