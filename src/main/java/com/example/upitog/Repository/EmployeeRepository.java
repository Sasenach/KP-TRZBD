package com.example.upitog.Repository;

import com.example.upitog.Model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findBySurnameContaining(String surname);
    Employee findByUsername(String username);
}
