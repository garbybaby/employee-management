package com.employee.management.manager;

import com.employee.management.dao.EmpPageDao;
import com.employee.management.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class EmpPageManagerImpl implements EmpPageManager {

    @Autowired
    EmpPageDao repository;

    public List<Employee> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Employee> pagedResult = repository.findAll(paging);

        if(pagedResult.hasContent()) {

            return pagedResult.getContent();
        } else {
            return new ArrayList<Employee>();
        }
    }
}
