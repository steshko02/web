package ru.steshko.springcourse.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;


public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @NotEmpty(message="Name should not be empty")
    @Size(min = 2,max=30, message = "Name should be between 2 and 30 characters")
    private String name;
    @NotEmpty(message="Surname should not be empty")
    @Size(min = 2,max=30, message = "Surname should be between 2 and 30 characters")
    private String surname;
    @NotEmpty(message="Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;
    @NotEmpty(message="Password should not be empty")
    @Size(min = 2,max=30, message = "Password should be between 2 and 30 characters")
    private String password;
    @Transient
    private String passwordConfirm;

    public User() {
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
