package com.ra.model.entity;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.util.Set;


@Entity
@Table(name = "masters")
public class Master {
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

    @Column(name = "information", nullable = false, length = 400)
    private String information;

    @Column(name = "completedOrdersCount", nullable = false)
    private int completedOrdersCount;

    @Column(name = "starsCount", nullable = false)
    private int starsCount;


    public Master(String email, String password, String firstname, String surname, String phone, String information, int completedOrdersCount, int starsCount) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.surname = surname;
        this.phone = phone;
        this.information = information;
        this.completedOrdersCount = completedOrdersCount;
        this.starsCount = starsCount;
    }

    public Master() {}


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

    public String getInformation() {
        return information;
    }

    public int getCompletedOrdersCount() {
        return completedOrdersCount;
    }

    public int getStarsCount() {
        return starsCount;
    }

    public void setCompletedOrdersCount(int completedOrdersCount) {
        this.completedOrdersCount = completedOrdersCount;
    }

    public void setStarsCount(int starsCount) {
        this.starsCount = starsCount;
    }

    @Override
    public String toString() {
        return "Master{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", information='" + information + '\'' +
                ", completedOrdersCount=" + completedOrdersCount +
                ", starsCount=" + starsCount +
                '}';
    }
}
