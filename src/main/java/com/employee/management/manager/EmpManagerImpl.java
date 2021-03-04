package com.employee.management.manager;

import com.employee.management.dao.EmpDao;
import com.employee.management.dto.EmployeeDto;
import com.employee.management.model.Employee;
import com.employee.management.util.Consumer;
import com.employee.management.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class EmpManagerImpl implements EmpManager {


    @Autowired
    EmpDao empDao;

    @Autowired
    Util util;

    @Autowired
    Consumer consumer;
    private static final Logger logger = LogManager.getLogger(EmpManagerImpl.class);


    private final String MSG_PRfX = "EmpManagerImpl#";

    @Override
    public List<Employee> getAllEmployees() throws Exception {

        String mtd_pstfx = "getAllEmployees()";
        logger.info("Enter " + MSG_PRfX + mtd_pstfx);

        List<Employee> employees = empDao.findAll();

        logger.info("List Employee " + employees.size());
        logger.info("Exit " + MSG_PRfX + mtd_pstfx);
        return employees;
    }

    @Override
    public EmployeeDto getEmployeeById(Integer id) throws Exception {
        Optional<Employee> emp = empDao.findById(id);
        if (emp.get() != null) {
            return util.convertToDto(emp.get());
        } else {
            return new EmployeeDto();
        }
    }


    @Override
    public EmployeeDto createOrUpdateEmployee(EmployeeDto employeeDto) throws Exception {

        String mtd_pstfx = "createOrUpdateEmployee()";
        logger.info("Enter " + MSG_PRfX + mtd_pstfx);
        logger.info("Employee  " + employeeDto);
        Employee empEntity = new Employee();
        if (employeeDto.getId() == null) {
            empEntity = util.convertToEntity(employeeDto);
            empEntity = empDao.save(empEntity);
            logger.info("Employee  Save  Success" + empEntity.getId());
            logger.info("Exit " + MSG_PRfX + mtd_pstfx);
            return util.convertToDto(empEntity);
        } else {
            Optional<Employee> employeeFromDb = empDao.findById(employeeDto.getId());
            if (employeeFromDb.isPresent()) {
                Employee updatae = employeeFromDb.get();
                updatae.setFirstName(employeeDto.getFirstName());
                updatae.setLastName(employeeDto.getLastName());
                updatae.setAddress(employeeDto.getAddress());
                updatae.setState(employeeDto.getState());
                updatae.setCountry(employeeDto.getCountry());
                updatae.setDepartment(employeeDto.getDepartment());
                updatae = empDao.save(updatae);
                logger.info("Employee  " + updatae.getId());
                logger.info("Exit " + MSG_PRfX + mtd_pstfx);
                return util.convertToDto(updatae);
            } else {
                empEntity = util.convertToEntity(employeeDto);
                empEntity = empDao.save(empEntity);
                logger.info("Employee  " + empEntity.getId());
                logger.info("Exit " + MSG_PRfX + mtd_pstfx);
                return util.convertToDto(empEntity);
            }
        }


    }

    @Override
    public Integer createBulkEmployee() throws Exception {

        String mtd_pstfx = "createBulkEmployee()";
        logger.info("Enter " + MSG_PRfX + mtd_pstfx);
//        logger.info("Employee  "+employee);
        Integer bulkCount = 100;
        for (int i = 0; i < bulkCount; i++) {
            Employee newBulk = new Employee();
            newBulk.setFirstName(i + "FirstName");
            newBulk.setLastName(i + "LastName");
            newBulk.setAddress(i + "Address");
            newBulk.setState(i + "tState");
            newBulk.setCountry(i + i + "");
            newBulk.setZipcode(i + i + i + "");
            newBulk.setDepartment(i + "Department");
            newBulk = empDao.save(newBulk);
            logger.info("Employee  " + newBulk.getId());

        }
        logger.info("Exit " + MSG_PRfX + mtd_pstfx);
        return bulkCount;
    }


    @Override
    public void deleteEmployeeById(Integer empId) throws Exception {

        Optional<Employee> employee = empDao.findById(empId);

        if (employee.isPresent()) {
            empDao.deleteById(empId);
        } else {
            throw new EntityNotFoundException("No employee record exist for given id");
        }
    }
}
