package com.example.upitog.Model;

import jdk.dynalink.linker.LinkerServices;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Поле не может быть пустым!")
    @Size(max = 30, message = "Mаксимальное количество символов 30")
    private String name;
    @Pattern(regexp = "[0-9][0-9]\\.[0-9][0-9]\\.[0-9][0-9][0-9][0-9]", message = "Формат даты дд.мм.гггг")
    @NotBlank(message = "Поле не может быть пустым!")
    private String expirationDate;
    @OneToMany(mappedBy = "productVedomost", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vedomost> vedomostList;
    @OneToMany(mappedBy = "productRealization", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Realization> realizationList ;

    public Product() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public List<Vedomost> getVedomostList() {
        return vedomostList;
    }

    public void setVedomostList(List<Vedomost> vedomostList) {
        this.vedomostList = vedomostList;
    }

    public List<Realization> getRealizationList() {
        return realizationList;
    }

    public void setRealizationList(List<Realization> realizationList) {
        this.realizationList = realizationList;
    }
}
