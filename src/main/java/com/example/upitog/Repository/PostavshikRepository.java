package com.example.upitog.Repository;

import com.example.upitog.Model.Postavshik;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostavshikRepository extends CrudRepository<Postavshik, Long> {
    List<Postavshik> findPostavshikByOrgNameContaining(String orgName);
    Postavshik findPostavshikByOrgName(String orgName);
}
