package com.example.upitog.Repository;


import com.example.upitog.Model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByNameContaining(String name);
}
