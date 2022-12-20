package com.example.upitog.Model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Поле не может быть пустым!")
    @Size(max = 30, message = "Mаксимальное количество символов 30")
    private String title;
    @NotNull(message = "Поле не может быть пустым")
    @Min(value = 1, message ="Заработная плата не может быть меньше 1р")
    private double salary;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Employee> employeeList;

    public Post() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
