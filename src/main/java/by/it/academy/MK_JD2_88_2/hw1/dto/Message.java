package by.it.academy.MK_JD2_88_2.hw1.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {

    private Long id;
    private String senderLogin;
    private String recipientLogin;
    private String text;
    private LocalDateTime dateTime;
    private User author;
    private User user;

    public Message(Long id, String senderLogin, String recipientLogin, String text, LocalDateTime dateTime) {
        this.id = id;
        this.senderLogin = senderLogin;
        this.recipientLogin = recipientLogin;
        this.text = text;
        this.dateTime = dateTime;
    }

    public Message(String senderLogin, String recipientLogin, String text, LocalDateTime dateTime) {
        this.senderLogin = senderLogin;
        this.recipientLogin = recipientLogin;
        this.text = text;
        this.dateTime = dateTime;
    }

    private Message(Long id, String senderLogin, String recipientLogin, String text, LocalDateTime dateTime, User author, User user) {
        this.id = id;
        this.senderLogin = senderLogin;
        this.recipientLogin = recipientLogin;
        this.text = text;
        this.dateTime = dateTime;
        this.author = author;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenderLogin() {
        return senderLogin;
    }

    public void setSenderLogin(String senderLogin) {
        this.senderLogin = senderLogin;
    }

    public String getRecipientLogin() {
        return recipientLogin;
    }

    public void setRecipientLogin(String recipientLogin) {
        this.recipientLogin = recipientLogin;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(senderLogin, message.senderLogin)
                && Objects.equals(id, message.id)
                && Objects.equals(recipientLogin, message.recipientLogin)
                && Objects.equals(text, message.text)
                && Objects.equals(dateTime, message.dateTime)
                && Objects.equals(author, message.author)
                && Objects.equals(user, message.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(senderLogin, recipientLogin, text, dateTime, id, author, user);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", senderLogin='" + senderLogin + '\'' +
                ", recipientLogin='" + recipientLogin + '\'' +
                ", text='" + text + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }

    public static class Builder {
        private Long id;
        private String senderLogin;
        private String recipientLogin;
        private String text;
        private LocalDateTime dateTime;
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

        public Builder setSenderLogin(String senderLogin) {
            this.senderLogin = senderLogin;
            return this;
        }

        public Builder setRecipientLogin(String recipientLogin) {
            this.recipientLogin = recipientLogin;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
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

        public Message build() {
            return new Message(id, senderLogin, recipientLogin, text, dateTime, author, user);
        }
    }

}
