package com.example.upitog.Repository;

import com.example.upitog.Model.DishInCheck;
import org.springframework.data.repository.CrudRepository;

public interface DishInCheckRepository extends CrudRepository<DishInCheck, Long> {
    long countBydish_id(long id);
}
