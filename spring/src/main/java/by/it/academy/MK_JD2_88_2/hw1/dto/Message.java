package by.it.academy.MK_JD2_88_2.hw1.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {

    private Long id;
    private String text;
    private LocalDateTime dateTime;
    private User sender;
    private User recipient;

    private Message(Long id, String text, LocalDateTime dateTime, User user, User author) {
        this.id = id;
        this.text = text;
        this.dateTime = dateTime;
        this.sender = user;
        this.recipient = author;
    }

    public Message() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id)
                && Objects.equals(text, message.text)
                && Objects.equals(dateTime, message.dateTime)
                && Objects.equals(sender, message.sender)
                && Objects.equals(recipient, message.recipient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, dateTime, sender, recipient);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", dateTime=" + dateTime +
                ", user=" + sender +
                ", author=" + recipient +
                '}';
    }

    public static class Builder {
        private Long id;
        private String text;
        private LocalDateTime dateTime;
        private User sender;
        private User recipient;

        private Builder() {
        }

        public static Builder createBuilder() {
            return new Builder();
        }

        public Builder setId(Long id) {
            this.id = id;
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

        public Builder setSender(User sender) {
            this.sender = sender;
            return this;
        }

        public Builder setRecipient(User recipient) {
            this.recipient = recipient;
            return this;
        }

        public Message build() {
            return new Message(id, text, dateTime, sender, recipient);
        }
    }

}
