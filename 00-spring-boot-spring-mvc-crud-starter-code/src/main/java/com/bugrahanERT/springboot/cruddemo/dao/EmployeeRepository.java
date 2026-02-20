package com.bugrahanERT.springboot.cruddemo.dao;

import com.bugrahanERT.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // that's it ... no need to write any code LOL!

    // ADD A METHOD TO SORT BY LAST NAME
    public List<Employee> findAllByOrderByLastNameAsc();

}
