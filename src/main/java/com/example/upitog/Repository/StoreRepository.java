package com.example.upitog.Repository;


import com.example.upitog.Model.Store;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StoreRepository extends CrudRepository<Store, Long> {
    List<Store> findByCapacityGreaterThan(int capacity);
    Store findBySkladNumber(int number);
}
