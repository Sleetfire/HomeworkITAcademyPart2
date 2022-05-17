package by.it.academy.MK_JD2_88_2.hw1.service;

import by.it.academy.MK_JD2_88_2.hw1.dto.Message;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IMessageService;
import by.it.academy.MK_JD2_88_2.hw1.storage.DBMessageStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.MessageStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IMessageStorage;

import java.util.*;

public class MessageService implements IMessageService {

    private static final IMessageService instance = new MessageService();
    private final Map<String, List<Message>> messagesMap = new HashMap<>();
    private final IMessageStorage storage = DBMessageStorage.getInstance();

    private MessageService() {
    }

    @Override
    public void createMessage(Message message) {
       this.storage.create(message);
    }

    @Override
    public List<Message> getAllMessages() {
        return this.storage.getAll();
    }

    @Override
    public List<Message> getMessagesBySenderLogin(String login) {
       return this.storage.getBySenderLogin(login);
    }

    @Override
    public List<Message> getMessagesByRecipientLogin(String login) {
        return this.storage.getByRecipientLogin(login);
    }

    @Override
    public int getMessagesCount() {
        return this.storage.getCount();
    }

    @Override
    public void deleteMessagesByUserLogin(String login) {
        this.storage.delete(login);
    }

    public static IMessageService getInstance() {
        return instance;
    }
}
