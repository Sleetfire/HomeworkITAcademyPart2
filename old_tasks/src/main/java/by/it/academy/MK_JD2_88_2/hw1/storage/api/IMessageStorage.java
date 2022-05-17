package by.it.academy.MK_JD2_88_2.hw1.storage.api;

import by.it.academy.MK_JD2_88_2.hw1.dto.Message;

import java.util.List;

public interface IMessageStorage {

    void create(Message message);

    List<Message> getAll();

    List<Message> getBySenderLogin(String login);

    List<Message> getByRecipientLogin(String login);

    int getCount();

    void delete(String login);

}
