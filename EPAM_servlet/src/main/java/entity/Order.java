package entity;

import java.sql.Date;
import java.text.DecimalFormat;

public class Order {
    private final int id;
    private final String title;
    private final String description;
    private final int cost;
    private final OrderState state;
    private final Date creationTime;
    private final int reviewStars;
    private final String reviewText;
    private final String userEmail;
    private final String masterEmail;



    public Order(int id, String title, String description, int cost, OrderState state, Date creationTime, String reviewText, int reviewStars, String userEmail, String masterEmail) {
        this.id = id;
        this.title = title;
        this.description =description;
        this.cost = cost;
        this.state = state;
        this.creationTime = creationTime;
        this.reviewText = reviewText;
        this.reviewStars = reviewStars;
        this.userEmail = userEmail;
        this.masterEmail = masterEmail;
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
                ", userEmail='" + userEmail + '\'' +
                ", masterEmail='" + masterEmail + '\'' +
                '}';
    }

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

    public OrderState getState() {
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

    public String getUserEmail() {
        return userEmail;
    }

    public String getMasterEmail() {
        return masterEmail;
    }


    public String getFormattedCost() {
        return new DecimalFormat("######.##").format(cost/100.0);
    }

    public Order changeState(OrderState orderState) {
        return new Order(id, title, description, cost, orderState, creationTime, reviewText, reviewStars, userEmail, masterEmail);
    }
}
