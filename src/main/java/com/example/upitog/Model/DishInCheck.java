package com.example.upitog.Model;

import javax.persistence.*;

@Entity
public class DishInCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Dish dish;
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee dishInCheckEmployee;

    public DishInCheck() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Employee getDishInCheckEmployee() {
        return dishInCheckEmployee;
    }

    public void setDishInCheckEmployee(Employee dishInCheckEmployee) {
        this.dishInCheckEmployee = dishInCheckEmployee;
    }
}
