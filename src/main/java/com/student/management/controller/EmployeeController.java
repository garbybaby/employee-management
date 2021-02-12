package com.student.management.controller;

import com.student.management.manager.EmpManager;
import com.student.management.model.Employee;
import com.student.management.util.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    EmpManager empManager;

    @Autowired
    Producer producer;

    @RequestMapping("")
    public ModelAndView getAllEmployees(ModelAndView model) {
        try {
            List<Employee> list = empManager.getAllEmployees();
            model.addObject("employees", list);
            model.setViewName("list-employees");
        } catch (Exception ex) {
            System.out.println("Exception " + ex);
        } finally {
            model.setViewName("list-employees");
        }
        return model;
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public ModelAndView editEmployeeById(ModelAndView model, @PathVariable("id") Optional<Integer> id) {
        try {
            if (id.isPresent()) {
                Employee entity = empManager.getEmployeeById(id.get());
                model.addObject("employee", entity);
            } else {
                model.addObject("employee", new Employee());
            }
            model.setViewName("add-edit-employee");
        } catch (Exception ex) {
            System.out.println("Exception " + ex);
        }
        return model;
    }

    @RequestMapping(path = "/createEmployee", method = RequestMethod.POST)
    public ModelAndView createOrUpdateEmployee(Employee employee) {
        ModelAndView model = new ModelAndView();
        try {
            System.out.println("Enter EmployeeController#createOrUpdateEmployee()");
            producer.produceMsg(employee);
//            empManager.createOrUpdateEmployee(employee);
        } catch (Exception ex) {
            System.out.println("Exception " + ex);
        } finally {

            getAllEmployees(model);
        }
        return model;
    }

    @RequestMapping(path = "/delete/{id}")
    public ModelAndView deleteEmployeeById(@PathVariable("id") Integer id)
            throws Exception {
        ModelAndView model = new ModelAndView();
        try {
            empManager.deleteEmployeeById(id);
        } catch (Exception ex) {
            System.out.println("Exception " + ex);
        } finally {
            getAllEmployees(model);
        }
        return model;
    }
}
