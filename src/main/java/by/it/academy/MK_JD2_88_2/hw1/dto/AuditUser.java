package by.it.academy.MK_JD2_88_2.hw1.dto;

import java.time.LocalDateTime;

public class AuditUser {

    private Long id;
    private LocalDateTime dtCreate;
    private String text;
    private User author;
    private User user;

    public AuditUser(Long id, LocalDateTime dtCreate, String text, User author, User user) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.text = text;
        this.author = author;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public String getText() {
        return text;
    }

    public User getAuthor() {
        return author;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "AuditUser{" +
                "id=" + id +
                ", dtCreate=" + dtCreate +
                ", text='" + text + '\'' +
                ", author=" + author +
                ", user=" + user +
                '}';
    }

    public static class Builder {
        private Long id;
        private LocalDateTime dtCreate;
        private String text;
        private User author;
        private User user;

        private Builder() {

        }

        public static Builder createBuilder() {
            return new Builder();
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setDtCreate(LocalDateTime dtCreate) {
            this.dtCreate = dtCreate;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setAuthor(User author) {
            this.author = author;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public AuditUser build() {
            return new AuditUser(this.id, this.dtCreate, this.text, this.author, this.user);
        }
    }
}
