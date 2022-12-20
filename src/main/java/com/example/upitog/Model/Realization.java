package com.example.upitog.Model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Realization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull(message = "Поле не может быть пустым")
    @Min(value = 0, message ="Колличество реализуемых продуктов не может быть меньше 1кг")
    private int Amount;
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employeeRealization;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product productRealization;
    @ManyToOne(fetch = FetchType.LAZY)
    private Dish dishRealization;

    public Realization() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public Employee getEmployeeRealization() {
        return employeeRealization;
    }

    public void setEmployeeRealization(Employee employeeRealization) {
        this.employeeRealization = employeeRealization;
    }

    public Product getProductRealization() {
        return productRealization;
    }
    public void setProductRealization(Product productRealization) {
        this.productRealization = productRealization;
    }

    public Dish getDishRealization() {
        return dishRealization;
    }

    public void setDishRealization(Dish dishRealization) {
        this.dishRealization = dishRealization;
    }
}
