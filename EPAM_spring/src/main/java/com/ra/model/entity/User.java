package com.ra.model.entity;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 20)
    private String password;

    @Column(name = "firstname", nullable = false, length = 20)
    private String firstname;

    @Column(name = "surname", nullable = false, length = 20)
    private String surname;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "balance", nullable = false)
    private int balance;


    public User(String email, String password, String firstname, String surname, String phone, int balance) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.surname = surname;
        this.phone = phone;
        this.balance = balance;
    }

    public User() {}



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

    public String getPhone() {
        return phone;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
