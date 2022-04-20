package by.it.academy.MK_JD2_88_2.hw1.service;

import by.it.academy.MK_JD2_88_2.hw1.model.Message;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IMessageService;
import by.it.academy.MK_JD2_88_2.hw1.repository.IMessageRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("messageService")
public class MessageService implements IMessageService {

    private IMessageRepository messageRepository;

    private MessageService(IMessageRepository messageRepository) {
         this.messageRepository = messageRepository;
    }

    @Override
    public void create(Message message) {
       this.messageRepository.save(message);
    }

    @Override
    public List<Message> getAll() {
        return this.messageRepository.findAll();
    }

    @Override
    public List<Message> getBySenderLogin(String login) {
       return this.messageRepository.findBySenderLogin(login);
    }

    @Override
    public List<Message> getByRecipientLogin(String login) {
        return this.messageRepository.findByRecipientLogin(login);
    }

    @Override
    public int getCount() {
        return Math.toIntExact(this.messageRepository.count());
    }

    @Override
    public void deleteByUserLogin(String login) {
        this.messageRepository.deleteBySenderLogin(login);
    }

}
