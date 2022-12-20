package com.example.upitog.Model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull(message = "Поле не может быть пустым")
    @Min(value = 1, message ="Вместимость не может быть меньше 1кг")
    private double capacity;
    @NotNull(message = "Поле не может быть пустым")
    private int skladNumber;
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Postavka> postavkaList;

    public Store() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public int getSkladNumber() {
        return skladNumber;
    }

    public void setSkladNumber(int skladNumber) {
        this.skladNumber = skladNumber;
    }

    public List<Postavka> getPostavkaList() {
        return postavkaList;
    }

    public void setPostavkaList(List<Postavka> postavkaList) {
        this.postavkaList = postavkaList;
    }


}
