package by.it.academy.MK_JD2_88_2.hw1.service.api;

import by.it.academy.MK_JD2_88_2.hw1.dto.Message;

import java.util.List;

public interface IMessageService {

    /**
     * Creating a message
     * @param message
     */
    void create(Message message);

    /**
     * Getting all messages
     * @return list of messages
     */
    List<Message> getAll();

    /**
     * Getting messages by sender's login
     * @param login
     * @return list of messages with current login
     */
    List<Message> getBySenderLogin(String login);

    /**
     * Getting messages by recipient's login
     * @param login
     * @return list of messages with current login
     */
    List<Message> getByRecipientLogin(String login);

    /**
     * Getting count of messages
     * @return
     */
    int getCount();

    /**
     * Deleting messages by current login
     * @param login
     */
    void deleteByUserLogin(String login);

}
