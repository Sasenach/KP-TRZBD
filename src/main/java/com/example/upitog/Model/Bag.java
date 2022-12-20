package com.example.upitog.Model;

import javax.persistence.*;

@Entity
public class Bag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee bagEmployee;
    @ManyToOne(fetch = FetchType.LAZY)
    private Dish bagDish;

    public Bag() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Employee getBagUser() {
        return bagEmployee;
    }

    public void setBagUser(Employee bagUser) {
        this.bagEmployee = bagUser;
    }

    public Dish getBagDish() {
        return bagDish;
    }

    public void setBagDish(Dish bagDish) {
        this.bagDish = bagDish;
    }
}
