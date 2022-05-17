package by.it.academy.MK_JD2_88_2.hw1.dto;

import java.time.LocalDate;
import java.util.Objects;

public class User {

    private String login;
    private String password;
    private String name;
    private LocalDate birthday;

    public User(String login, String password, String name, LocalDate birthday) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(this.login, user.login) && Objects.equals(this.name, user.name)
                && Objects.equals(this.birthday, user.birthday) && Objects.equals(this.password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, name, birthday, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", password='" + password + '\'' +
                '}';
    }
}
