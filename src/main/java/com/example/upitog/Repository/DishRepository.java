package com.example.upitog.Repository;

import com.example.upitog.Model.Dish;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DishRepository extends CrudRepository<Dish, Long> {
    List<Dish> findByNameContaining(String name);
}
