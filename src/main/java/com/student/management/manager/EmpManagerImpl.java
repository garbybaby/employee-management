package com.student.management.manager;

import com.student.management.dao.EmpDao;
import com.student.management.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpManagerImpl implements EmpManager{


    @Autowired
    EmpDao empDao;

    @Override
    public List<Employee> getAllEmployees()throws Exception {

        List<Employee> employees=new ArrayList<>();
       employees= empDao.findAll();
       System.out.println("List Employee "+employees);
        return employees;
    }

    @Override
    public Employee getEmployeeById(Integer id) throws Exception {
        Optional<Employee> emp=empDao.findById(id);
        return emp.get();
    }


    @Override
    public Employee createOrUpdateEmployee(Employee employee) throws Exception{

        if(employee.getId()  == null)
        {
            employee = empDao.save(employee);
            return employee;
        }
        else
        {
            Optional<Employee>  employeeFromDb = empDao.findById(employee.getId());
            if(employeeFromDb.isPresent())
            {
                Employee updatae=employeeFromDb.get();
                updatae.setFirstName(employee.getFirstName());
                updatae.setLastName(employee.getLastName());
                updatae.setAddress(employee.getAddress());
                updatae.setState(employee.getState());
                updatae.setCountry(employee.getCountry());
                updatae.setDepartment(employee.getDepartment());
                updatae = empDao.save(updatae);
                return updatae;
            } else {
                employee = empDao.save(employee);
                return employee;
            }
        }
    }

    @Override
    public void deleteEmployeeById(Integer empId)throws Exception {

        Optional<Employee> employee = empDao.findById(empId);

        if(employee.isPresent())
        {
            empDao.deleteById(empId);
        } else {
            throw new EntityNotFoundException("No employee record exist for given id");
        }
    }
}
