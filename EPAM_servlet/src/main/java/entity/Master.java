package entity;

import java.text.DecimalFormat;

public class Master {
    private final String email;
    private final String password;
    private final String firstname;
    private final String surname;
    private final String phone;
    private final String information;
    private final int completedOrdersCount;
    private final int starsCount;

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




    public String getFormattedAverageScore() {
        if (completedOrdersCount == 0) return "0";
        return new DecimalFormat("#.#").format((double)starsCount/completedOrdersCount);
    }

    public String getFormattedAverageScoreInPercents() {
        if (completedOrdersCount == 0) return "0";
        return String.valueOf(20*starsCount/completedOrdersCount);
    }

    public Master changeFeedbackData(int stars) {
        return new Master(email, password, firstname, surname, phone, information, completedOrdersCount+1, starsCount+stars);
    }
}
