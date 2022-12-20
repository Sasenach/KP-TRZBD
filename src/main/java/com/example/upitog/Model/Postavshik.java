package com.example.upitog.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Postavshik {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 1, max = 50, message = "Поле может содержать от 5 до 50 символов")
    private String orgName;
    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 1, max = 30, message = "Поле может содержать от 1 до 30 символов")
    private String surname;
    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 1, max = 30, message = "Поле может содержать от 1 до 30 символов")
    private String name;
    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 1, max = 30, message = "Поле может содержать от 1 до 30 символов")
    private String midlename;
    @NotBlank(message = "Поле не может быть пустым!")
    @Pattern(regexp = ("[8][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]"), message = "Поле может содержать 11 цифр\nномера телефона с 8 (89167716131)")
    private String phoneNumber;
    @NotBlank(message = "Поле не может быть пустым!")
    private String email;
    @OneToMany(mappedBy = "postavshik", cascade = CascadeType.ALL)
    private List<Postavka> postavkasList;

    public Postavshik() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMidlename() {
        return midlename;
    }

    public void setMidlename(String midlename) {
        this.midlename = midlename;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Postavka> getPostavkasList() {
        return postavkasList;
    }

    public void setPostavkasList(List<Postavka> postavkasList) {
        this.postavkasList = postavkasList;
    }


}
