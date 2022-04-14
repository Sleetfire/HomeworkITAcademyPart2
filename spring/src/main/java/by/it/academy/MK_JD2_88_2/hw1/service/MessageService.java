package by.it.academy.MK_JD2_88_2.hw1.service;

import by.it.academy.MK_JD2_88_2.hw1.dto.Message;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IMessageService;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IFactoryStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IMessageStorage;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("messageService")
public class MessageService implements IMessageService {

    private IMessageStorage choiceFactoryStorage;

    private MessageService(IFactoryStorage choiceFactoryStorage) {
         this.choiceFactoryStorage = choiceFactoryStorage.getMessageStorage();
    }

    @Override
    public void create(Message message) {
       this.choiceFactoryStorage.add(message);
    }

    @Override
    public List<Message> getAll() {
        return this.choiceFactoryStorage.getAll();
    }

    @Override
    public List<Message> getBySenderLogin(String login) {
       return this.choiceFactoryStorage.getBySenderLogin(login);
    }

    @Override
    public List<Message> getByRecipientLogin(String login) {
        return this.choiceFactoryStorage.getByRecipientLogin(login);
    }

    @Override
    public int getCount() {
        return this.choiceFactoryStorage.getCount();
    }

    @Override
    public void deleteByUserLogin(String login) {
        this.choiceFactoryStorage.delete(login);
    }

}
