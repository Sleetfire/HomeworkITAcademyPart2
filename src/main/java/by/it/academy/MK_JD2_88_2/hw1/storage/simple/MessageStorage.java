package by.it.academy.MK_JD2_88_2.hw1.storage.simple;

import by.it.academy.MK_JD2_88_2.hw1.dto.Message;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IMessageStorage;

import java.util.*;
import java.util.stream.Collectors;

public class MessageStorage implements IMessageStorage {

    private final Map<String, List<Message>> messagesMap = new HashMap<>();
    private static final IMessageStorage instance = new MessageStorage();

    private MessageStorage() {

    }

    @Override
    public void add(Message message) {
        String login = message.getSender().getLogin();
        List<Message> messages = this.messagesMap.getOrDefault(login, new ArrayList<>());
        messages.add(message);
        this.messagesMap.put(login, messages);
    }

    @Override
    public List<Message> getAll() {
        List<Message> messages = new ArrayList<>();
        this.messagesMap.values().forEach(messages::addAll);
        return Collections.unmodifiableList(messages);
    }

    @Override
    public List<Message> getBySenderLogin(String login) {
        return getAll().stream()
                .filter(message -> Objects.equals(message.getSender().getLogin(), login))
                .collect(Collectors.toList());
    }

    @Override
    public List<Message> getByRecipientLogin(String login) {
        return this.messagesMap.get(login);
    }

    @Override
    public int getCount() {
        return getAll().size();
    }

    @Override
    public void delete(String login) {
        this.messagesMap.remove(login);
    }

    public static IMessageStorage getInstance() {
        return instance;
    }
}
