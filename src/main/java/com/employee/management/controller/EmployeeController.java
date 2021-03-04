package com.employee.management.controller;

import com.employee.management.dto.EmployeeDto;
import com.employee.management.manager.EmpManager;
import com.employee.management.model.Employee;
import com.employee.management.util.Producer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    EmpManager empManager;

    @Autowired
    Producer producer;
    private static final Logger logger = LogManager.getLogger(EmployeeController.class);

    private final String MSG_PRfX = "EmployeeController#";
    private final String EXCEPTION = "Exception Occurred #";

    @RequestMapping("")
    public ModelAndView getAllEmployees(ModelAndView model) {
        String mtd_pstfx = "getAllEmployees()";
        logger.info("Enter " + MSG_PRfX + mtd_pstfx);
        try {
            List<Employee> list = empManager.getAllEmployees();
            logger.info("List<Employee>  " + list.size());
            model.addObject("employees", list);
            model.addObject("empCount", list.size());
            model.setViewName("list-employees");
        } catch (Exception ex) {
            logger.info(EXCEPTION + ex);
        } finally {
            model.setViewName("list-employees");
        }
        logger.info("Exit " + MSG_PRfX + mtd_pstfx);
        return model;
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public ModelAndView editEmployeeById(ModelAndView model, @PathVariable("id") Optional<Integer> id) {
        try {
            if (id.isPresent()) {
                EmployeeDto entity = empManager.getEmployeeById(id.get());
                model.addObject("employee", entity);
            } else {
                model.addObject("employee", new Employee());
            }
            model.setViewName("add-edit-employee");
        } catch (Exception ex) {
            logger.info("Exception " + ex);
        }
        return model;
    }

    @RequestMapping(path = "/createEmployee", method = RequestMethod.POST)
    public ModelAndView createOrUpdateEmployee(EmployeeDto employee) {
        ModelAndView model = new ModelAndView();
        try {
            logger.info("Enter EmployeeController#createOrUpdateEmployee()");
            producer.produceMsg(employee);
        } catch (Exception ex) {
            logger.info(EXCEPTION + ex);
        } finally {

            getAllEmployees(model);
        }
        return model;
    }

    @RequestMapping(path = "/create-bulk", method = RequestMethod.POST)
    public String createBulk(@PathParam("startNum") Integer startNum, @PathParam("limit") Integer limit) {
        Integer bulkCount = 0;
        try {
            logger.info("Enter EmployeeController#createBulk()");
            logger.info("Bulk Create Details StartNum : " + startNum.toString() + "| Limit : " + limit.toString());
            List<EmployeeDto> bulkDtos = createBulkEmployee(startNum, limit);
            bulkCount = bulkDtos.size();
            logger.info("Size " + bulkCount.toString());
            for (int i = 0; i < bulkDtos.size(); i++) {
                logger.info("New bulk Empl");
                producer.produceMsg(bulkDtos.get(i));
            }
        } catch (Exception ex) {
            logger.info(EXCEPTION, ex);

        }
        return " Number of employees added : " + bulkCount;
    }

    @RequestMapping(path = "/delete/{id}")
    public ModelAndView deleteEmployeeById(@PathVariable("id") Integer id)
            throws Exception {
        ModelAndView model = new ModelAndView();
        try {
            empManager.deleteEmployeeById(id);
        } catch (Exception ex) {
            logger.info(EXCEPTION, ex);
        } finally {
            getAllEmployees(model);
        }
        return model;
    }

    public List<EmployeeDto> createBulkEmployee(Integer startNum, Integer limit) throws Exception {

        String mtd_pstfx = "createBulkEmployee()";
        logger.info("Enter " + MSG_PRfX + mtd_pstfx);

        List<EmployeeDto> employeeDtos = new ArrayList<>();
        for (Integer i = startNum; i < startNum + limit; i++) {
            EmployeeDto newBulk = new EmployeeDto();
            newBulk.setFirstName(i + "FirstName");
            newBulk.setLastName(i + "LastName");
            newBulk.setAddress(i + "Address");
            newBulk.setState(i + "tState");
            newBulk.setCountry(i + i + "");
            newBulk.setZipcode(i + i + i + "");
            newBulk.setDepartment(i + "Department");
            employeeDtos.add(newBulk);
        }
        logger.info("Exit " + MSG_PRfX + mtd_pstfx);
        return employeeDtos;
    }

}
