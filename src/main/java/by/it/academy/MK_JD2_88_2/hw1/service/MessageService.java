package by.it.academy.MK_JD2_88_2.hw1.service;

import by.it.academy.MK_JD2_88_2.hw1.dto.Message;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IMessageService;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.ChoiceFactoryStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IMessageStorage;

import java.util.*;

public class MessageService implements IMessageService {

    private static final IMessageService instance = new MessageService();
    private final IMessageStorage storage = ChoiceFactoryStorage.getInstance().getMessageStorage();

    private MessageService() {
    }

    @Override
    public void create(Message message) {
       this.storage.add(message);
    }

    @Override
    public List<Message> getAll() {
        return this.storage.getAll();
    }

    @Override
    public List<Message> getBySenderLogin(String login) {
       return this.storage.getBySenderLogin(login);
    }

    @Override
    public List<Message> getByRecipientLogin(String login) {
        return this.storage.getByRecipientLogin(login);
    }

    @Override
    public int getCount() {
        return this.storage.getCount();
    }

    @Override
    public void deleteByUserLogin(String login) {
        this.storage.delete(login);
    }

    public static IMessageService getInstance() {
        return instance;
    }
}
