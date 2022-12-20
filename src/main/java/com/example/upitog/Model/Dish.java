package com.example.upitog.Model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull(message = "Поле не может быть пустым")
    @Min(value = 0, message ="Стоимость не может быть отрицательной")
    private int cost;
    @NotBlank(message = "Поле не должно быть пустым!")
    private String name;
    @NotBlank(message = "Поле не должно быть пустым!")
    private String photoUrl;
    @NotBlank(message = "Поле не должно быть пустым!")
    private String description;
    @NotBlank(message = "Поле не должно быть пустым!")
    private String tipe;
    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL)
    private List<DishInCheck> dishInCheckListList;
    @OneToMany(mappedBy = "dishRealization", cascade = CascadeType.ALL)
    private List<Realization> realizationList;
    @OneToMany(mappedBy = "bagDish", cascade = CascadeType.ALL)
    private List<Bag> bags;

    public Dish() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DishInCheck> getDishInCheckListList() {
        return dishInCheckListList;
    }

    public void setDishInCheckListList(List<DishInCheck> dishInCheckListList) {
        this.dishInCheckListList = dishInCheckListList;
    }

    public List<Realization> getRealizationList() {
        return realizationList;
    }

    public void setRealizationList(List<Realization> realizationList) {
        this.realizationList = realizationList;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }
}
