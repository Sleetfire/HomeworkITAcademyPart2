package by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(schema = "test", name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<MessageEntity> messages = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<AuditUserEntity> userAudits = new ArrayList<>();

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<AuditUserEntity> authorAudits = new ArrayList<>();

    private UserEntity(Long id, String login, String password, String name, LocalDate rgDate, LocalDate birthday) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.rgDate = rgDate;
        this.birthday = birthday;
    }

    public UserEntity() {
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

    public Set<MessageEntity> getMessages() {
        return messages;
    }

    public void setMessages(Set<MessageEntity> messages) {
        this.messages = messages;
    }

    public List<AuditUserEntity> getUserAudits() {
        return userAudits;
    }

    public void setUserAudits(List<AuditUserEntity> userAudits) {
        this.userAudits = userAudits;
    }

    public List<AuditUserEntity> getAuthorAudits() {
        return authorAudits;
    }

    public void setAuthorAudits(List<AuditUserEntity> authorAudits) {
        this.authorAudits = authorAudits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity user = (UserEntity) o;
        return Objects.equals(login, user.login)
                && Objects.equals(id, user.id)
                && Objects.equals(password, user.password)
                && Objects.equals(name, user.name)
                && Objects.equals(rgDate, user.rgDate)
                && Objects.equals(birthday, user.birthday)
                && Objects.equals(messages, user.messages)
                && Objects.equals(userAudits, user.userAudits)
                && Objects.equals(authorAudits, user.authorAudits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, name, rgDate, birthday, id, messages, userAudits, authorAudits);
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
                ", messages=" + messages +
                ", userAudits=" + userAudits +
                ", authorAudits=" + authorAudits +
                '}';
    }

    public static class Builder {
        private Long id;
        private String login;
        private String password;
        private String name;
        private LocalDate rgDate;
        private LocalDate birthday;


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

        public UserEntity build() {
            return new UserEntity(this.id, this.login, this.password, this.name, this.rgDate, this.birthday);
        }
    }
}
