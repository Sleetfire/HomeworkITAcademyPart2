package by.it.academy.MK_JD2_88_2.hw1.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "users", schema = "test")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "dt_rg")
    private LocalDate rgDate;
    @Column(name = "birthday")
    private LocalDate birthday;
    @Column(name = "update_dt")
    private LocalDateTime updateDateTime;

    public User(Long id, String login, String password, String name, LocalDate rgDate, LocalDate birthday, LocalDateTime updateDateTime) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.rgDate = rgDate;
        this.birthday = birthday;
        this.updateDateTime = updateDateTime;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getRgDate() {
        return rgDate;
    }

    public void setRgDate(LocalDate rgDate) {
        this.rgDate = rgDate;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login)
                && Objects.equals(id, user.id)
                && Objects.equals(password, user.password)
                && Objects.equals(name, user.name)
                && Objects.equals(rgDate, user.rgDate)
                && Objects.equals(birthday, user.birthday)
                && Objects.equals(updateDateTime, user.updateDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, name, rgDate, birthday, id, updateDateTime);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", rgDate=" + rgDate +
                ", birthday=" + birthday +
                ", updateDateTime=" + updateDateTime +
                '}';
    }

    public static class Builder {
        private Long id;
        private String login;
        private String password;
        private String name;
        private LocalDate rgDate;
        private LocalDate birthday;
        private LocalDateTime updateDateTime;

        private Builder() {
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public static Builder createBuilder() {
            return new Builder();
        }

        public Builder setLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setRgDate(LocalDate rgDate) {
            this.rgDate = rgDate;
            return this;
        }

        public Builder setBirthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder setUpdateDateTime(LocalDateTime updateDateTime) {
            this.updateDateTime = updateDateTime;
            return this;
        }

        public User build() {
            return new User(this.id, this.login, this.password, this.name, this.rgDate, this.birthday, this.updateDateTime);
        }
    }
}
