package by.it.academy.MK_JD2_88_2.hw1.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {

    private String senderLogin;
    private String recipientLogin;
    private String text;
    private LocalDateTime dateTime;

    public Message(String senderLogin, String recipientLogin, String text, LocalDateTime dateTime) {
        this.senderLogin = senderLogin;
        this.recipientLogin = recipientLogin;
        this.text = text;
        this.dateTime = dateTime;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(senderLogin, message.senderLogin)
                && Objects.equals(recipientLogin, message.recipientLogin) && Objects.equals(text, message.text)
                && Objects.equals(dateTime, message.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(senderLogin, recipientLogin, text, dateTime);
    }

    @Override
    public String toString() {
        return "Message{" +
                "senderLogin='" + senderLogin + '\'' +
                ", recipientLogin='" + recipientLogin + '\'' +
                ", text='" + text + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
