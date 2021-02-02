package com.student.management.dao;

import com.student.management.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpDao extends CrudRepository<Employee, Integer> {

    List<Employee> findAll();

    Optional<Employee> findById(Integer id);

    void deleteById(Integer id);
}
