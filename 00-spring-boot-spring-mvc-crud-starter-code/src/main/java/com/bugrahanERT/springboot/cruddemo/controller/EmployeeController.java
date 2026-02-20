package com.bugrahanERT.springboot.cruddemo.controller;


import com.bugrahanERT.springboot.cruddemo.entity.Employee;
import com.bugrahanERT.springboot.cruddemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    // add mapping for "/list"

    @GetMapping("/list")
    public String listEmployees(Model theModel) {

        // get the employees from db
        List<Employee> theEmployees = employeeService.findAll();

        // add to the spring model
        theModel.addAttribute("employees", theEmployees);

        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){

        // CREATE MODEL ATTRİBUTE TO BİND FORM DATA
        Employee theEmployee= new Employee();

        theModel.addAttribute("employee", theEmployee);

        return "employees/employee-form";

    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute ("employee") Employee theEmployee){

        // SAVE THE EMPLOYEE
        employeeService.save(theEmployee);

        // UAE A REDİRECT TO PREVENT DUPLİCATE SUBMMSSİONS
        return "redirect:/employees/list";
    }
}
























