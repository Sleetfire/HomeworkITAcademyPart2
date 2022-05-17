package by.it.academy.MK_JD2_88_2.hw1.service.api;

import by.it.academy.MK_JD2_88_2.hw1.dto.Message;

import java.util.List;

public interface IMessageService {

    void createMessage(Message message);

    List<Message> getAllMessages();

    List<Message> getMessagesBySenderLogin(String login);

    List<Message> getMessagesByRecipientLogin(String login);

    int getMessagesCount();

    void deleteMessagesByUserLogin(String login);

}
