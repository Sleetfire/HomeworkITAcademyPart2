package by.it.academy.MK_JD2_88_2.hw1.storage.api;

import by.it.academy.MK_JD2_88_2.hw1.dto.Message;

import java.util.List;

public interface IMessageStorage {

    /**
     * Creating a message
     * @param message
     */
    void add(Message message);

    /**
     * Getting all messages
     * @return list of all messages
     */
    List<Message> getAll();

    /**
     * Getting messages by sender's login
     * @param login
     * @return list of messages with sender's login
     */
    List<Message> getBySenderLogin(String login);

    /**
     * Getting messages by recipient's login
     * @param login
     * @return list of messages with recipient's login
     */
    List<Message> getByRecipientLogin(String login);

    /**
     * Getting messages count
     * @return count
     */
    int getCount();

    /**
     * Deleting messages by user's login
     * @param login
     */
    void delete(String login);

}
