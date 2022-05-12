package entity;

public class Manager {
    private final String email;
    private final String password;
    private final String firstname;
    private final String surname;

    public Manager(String email, String password, String firstname, String surname) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "e_mail='" + getEmail() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", firstname='" + getFirstname() + '\'' +
                ", surname='" + getSurname() + '\'' +
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
}
