package com.employee.management.dao;

import com.employee.management.model.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpPageDao  extends PagingAndSortingRepository<Employee, Integer> {

}
