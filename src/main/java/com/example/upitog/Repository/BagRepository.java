package com.example.upitog.Repository;

import com.example.upitog.Model.Bag;
import com.example.upitog.Model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BagRepository extends CrudRepository<Bag, Long> {
    List<Bag> findByBagEmployee(Employee employee);
}
