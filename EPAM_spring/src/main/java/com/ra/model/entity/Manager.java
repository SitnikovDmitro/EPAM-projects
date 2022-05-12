package com.ra.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "managers")
public class Manager {
    @Id
    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 20)
    private String password;

    @Column(name = "firstname", nullable = false, length = 20)
    private String firstname;

    @Column(name = "surname", nullable = false, length = 20)
    private String surname;

    public Manager(String email, String password, String firstname, String surname) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.surname = surname;
    }

    public Manager() {}

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }
}
