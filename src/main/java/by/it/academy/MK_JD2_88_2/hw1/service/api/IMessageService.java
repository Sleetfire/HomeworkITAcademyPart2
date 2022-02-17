package by.it.academy.MK_JD2_88_2.hw1.service.api;

import by.it.academy.MK_JD2_88_2.hw1.dto.Message;

import java.util.List;

public interface IMessageService {

    /**
     * Adds message object in storage(List or Map)
     * @param message message object
     */
    void createMessage(Message message);

    /**
     * Returns a list with all user messages
     * @return list with user messages
     */
    List<Message> getAllMessages();

    /**
     * Returns a list with messages by sender
     * @param login sender's login
     * @return list with messages
     */
    List<Message> getMessagesBySenderLogin(String login);

    /**
     * Returns a list with messages by recipient
     * @param login recipient's login
     * @return list with messages
     */
    List<Message> getMessagesByRecipientLogin(String login);

    /**
     * Returns the number of messages in storage
     * @return number of messages
     */
    int getMessagesCount();

    /**
     * Deletes message from storage by user's login
     * @param login user's login
     */
    void deleteMessagesByUserLogin(String login);
}
