package com.example.upitog.Model;

import net.bytebuddy.implementation.bind.annotation.Default;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 1, max = 30, message = "Поле может содержать от 1 до 30 символов")
    private String surname;
    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 1, max = 30, message = "Поле может содержать от 1 до 30 символов")
    private String name;
    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 1, max = 30, message = "Поле может содержать от 1 до 30 символов")
    private String midlename;
    @Pattern(regexp = ("[0-9][0-9][0-9][0-9][ ][0-9][0-9][0-9][0-9][0-9][0-9]"),
            message = "Формат паспорта 1234 123456")
    @NotBlank(message = "Поле не должно быть пустым!")
    private String pasportNumber;
    @Size(max = 250, message = "Mаксимальное количество символов 250")
    @NotBlank
    private String address;
//    @Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", message = "Не соответствие формату почты")
    @Size(max = 250, message = "Mаксимальное количество символов 250")
    @NotBlank
    private String email;
//    @UniqueElements(message = "Этот логин уже занят, попробуйте другой :)")
    @NotBlank(message = "Поле не должно быть пустым!")
    private String username;
    @Pattern(regexp = "^(?=.*[0-9])" +
            "(?=.*[a-z])" +
            "(?=.*[A-Z])" +
            "(?=.*[!@#$%^&+=])" +
            "(?=\\S+$)" +
            ".{6,}", message = "Пароль должен быть не меньше 6 символов,"+ "\n" +
            "иметь числа и латинкие строчные и заглавные буквы," + "\n" +
            "а также специальные символы ")
    private String password;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;
    @OneToMany(mappedBy = "employeeVedomost", cascade = CascadeType.ALL)
    private List<Vedomost> vedomostList;
    @OneToMany(mappedBy = "employeeRealization", cascade = CascadeType.ALL)
    private List<Realization> realizationList;
    @OneToMany(mappedBy = "dishInCheckEmployee", cascade = CascadeType.ALL)
    private List<DishInCheck> dishInChecks;
    @OneToMany(mappedBy = "bagEmployee", cascade = CascadeType.ALL)
    private List<Bag> bags;

    public Employee() {
    }

    public Employee(String name, String address, String email, String login, String password) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.username = login;
        this.password = password;
        this.roles = Set.of(Role.USER);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getPasportNumber() {
        return pasportNumber;
    }

    public void setPasportNumber(String pasportNumber) {
        this.pasportNumber = pasportNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<DishInCheck> getDishInChecks() {
        return dishInChecks;
    }

    public void setDishInChecks(List<DishInCheck> dishInChecks) {
        this.dishInChecks = dishInChecks;
    }

    public List<Bag> getBags() {
        return bags;
    }

    public void setBags(List<Bag> bags) {
        this.bags = bags;
    }
}
