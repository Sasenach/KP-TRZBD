package com.example.upitog.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
public class Vedomost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Поле не может быть пустым!")
    private String datePriema;
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employeeVedomost;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product productVedomost;
    @ManyToOne(fetch = FetchType.LAZY)
    private Postavka postavkaVedomost;

    public Vedomost() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDatePriema() {
        return datePriema;
    }

    public void setDatePriema(String datePriema) {
        this.datePriema = datePriema;
    }

    public Employee getEmployeeVedomost() {
        return employeeVedomost;
    }

    public void setEmployeeVedomost(Employee employeeVedomost) {
        this.employeeVedomost = employeeVedomost;
    }

    public Product getProductVedomost() {
        return productVedomost;
    }

    public void setProductVedomost(Product productVedomost) {
        this.productVedomost = productVedomost;
    }

    public Postavka getPostavkaVedomost() {
        return postavkaVedomost;
    }

    public void setPostavkaVedomost(Postavka postavkaVedomost) {
        this.postavkaVedomost = postavkaVedomost;
    }


}
