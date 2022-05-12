package entity;

import java.text.DecimalFormat;

public final class User {
    private final String email;
    private final String password;
    private final String firstname;
    private final String surname;
    private final String phone;
    private final int balance;

    public User(String email, String password, String firstname, String surname, String phone, int balance) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.surname = surname;
        this.phone = phone;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "e_mail='" + getEmail() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", firstname='" + getFirstname() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", sum=" + getFormattedBalance() +
                '}';
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

    public int getBalance() {
        return balance;
    }



    public String getFormattedBalance() {
        return new DecimalFormat("######.##").format(balance/100.0);
    }

    public User changeBalance(int newBalance) {
        return new User(email, password, firstname, surname, phone, newBalance);
    }
}
