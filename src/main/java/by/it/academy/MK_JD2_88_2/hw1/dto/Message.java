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
        return this.senderLogin;
    }

    public void setSenderLogin(String senderLogin) {
        this.senderLogin = senderLogin;
    }

    public String getRecipientLogin() {
        return this.recipientLogin;
    }

    public void setRecipientLogin(String recipientLogin) {
        this.recipientLogin = recipientLogin;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(this.senderLogin, message.senderLogin)
                && Objects.equals(this.recipientLogin, message.recipientLogin) && Objects.equals(this.text, message.text)
                && Objects.equals(this.dateTime, message.dateTime);
    }

    @Override
    public int hashCode() {
        int result = 31 * this.senderLogin.hashCode();
        result += 31 * this.recipientLogin.hashCode();
        result += 31 * this.text.hashCode();
        result += this.dateTime.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "senderLogin='" + this.senderLogin + '\'' +
                ", recipientLogin='" + this.recipientLogin + '\'' +
                ", text='" + this.text + '\'' +
                ", dateTime=" + this.dateTime +
                '}';
    }
}
