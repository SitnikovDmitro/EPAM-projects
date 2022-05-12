package com.ra.model.entity;

import javax.persistence.*;
import java.sql.Date;
import java.text.DecimalFormat;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "description", nullable = false, length = 400)
    private String description;

    @Column(name = "cost", nullable = false)
    private int cost;

    @Column(name = "state", nullable = false)
    private int state;

    @Column(name = "creationTime", nullable = false)
    private Date creationTime;

    @Column(name = "reviewStars", nullable = false)
    private int reviewStars;

    @Column(name = "reviewText", nullable = false, length = 400)
    private String reviewText;

    @ManyToOne
    @JoinColumn(name = "userEmail", nullable = false, referencedColumnName = "email")
    private User user;

    @ManyToOne
    @JoinColumn(name = "masterEmail", referencedColumnName = "email")
    private Master master;


    public Order(int id, String title, String description, int cost, int state, Date creationTime, int reviewStars, String reviewText, User user, Master master) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.cost = cost;
        this.state = state;
        this.creationTime = creationTime;
        this.reviewStars = reviewStars;
        this.reviewText = reviewText;
        this.user = user;
        this.master = master;
    }

    public Order() {}


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCost() {
        return cost;
    }

    public int getState() {
        return state;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public String getReviewText() {
        return reviewText;
    }

    public int getReviewStars() {
        return reviewStars;
    }

    public User getUser() {
        return user;
    }

    public Master getMaster() {
        return master;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setReviewStars(int reviewStars) {
        this.reviewStars = reviewStars;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public String getEncodedId() {
        return String.valueOf(id);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", state=" + state +
                ", creationTime=" + creationTime +
                ", reviewStars=" + reviewStars +
                ", reviewText='" + reviewText + '\'' +
                ", user=" + user +
                ", master=" + master +
                '}';
    }


}
