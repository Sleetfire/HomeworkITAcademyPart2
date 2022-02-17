package by.it.academy.MK_JD2_88_2.hw1.service;

import by.it.academy.MK_JD2_88_2.hw1.dto.Message;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IMessageService;

import java.util.*;
import java.util.stream.Collectors;

public class MessageService implements IMessageService {

    private static final IMessageService instance = new MessageService();
    private final Map<String, List<Message>> messagesMap = new HashMap<>();

    private MessageService() {
    }

    @Override
    public void createMessage(Message message) {
        String login = message.getRecipientLogin();
        List<Message> messages = this.messagesMap.getOrDefault(login, new ArrayList<>());
        messages.add(message);
        this.messagesMap.put(login, messages);
    }

    @Override
    public List<Message> getAllMessages() {
        List<Message> messages = new ArrayList<>();
        this.messagesMap.values().forEach(messages::addAll);
        return Collections.unmodifiableList(messages);
    }

    @Override
    public List<Message> getMessagesBySenderLogin(String login) {
        return getAllMessages().stream()
                .filter(message -> Objects.equals(message.getSenderLogin(), login))
                .collect(Collectors.toList());
    }

    @Override
    public List<Message> getMessagesByRecipientLogin(String login) {
        return this.messagesMap.get(login);
    }

    @Override
    public int getMessagesCount() {
        return getAllMessages().size();
    }

    @Override
    public void deleteMessagesByUserLogin(String login) {
        this.messagesMap.remove(login);
    }

    public static IMessageService getInstance() {
        return instance;
    }
}
