package com.example.upitog.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
public class Postavka {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Pattern(regexp = "[0-9][0-9]\\.[0-9][0-9]\\.[0-9][0-9][0-9][0-9]", message = "Формат даты дд.мм.гггг")
    @NotBlank(message = "Поле не может быть пустым!")
    private String dataPostavki;
    @ManyToOne(fetch = FetchType.LAZY)
    private Postavshik postavshik;
    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;
    @OneToMany(mappedBy = "postavkaVedomost", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vedomost> tovarPostavkiList;

    public Postavka() {
    }

    public Postavka(int numberPostavki, String dataPostavki, Postavshik postavshik, Store store) {
        this.dataPostavki = dataPostavki;
        this.postavshik = postavshik;
        this.store = store;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getDataPostavki() {
        return dataPostavki;
    }

    public void setDataPostavki(String dataPostavki) {
        this.dataPostavki = dataPostavki;
    }

    public Postavshik getPostavshik() {
        return postavshik;
    }

    public void setPostavshik(Postavshik postavshik) {
        this.postavshik = postavshik;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public List<Vedomost> getTovarPostavkiList() {
        return tovarPostavkiList;
    }

    public void setTovarPostavkiList(List<Vedomost> tovarPostavkiList) {
        this.tovarPostavkiList = tovarPostavkiList;
    }


}
