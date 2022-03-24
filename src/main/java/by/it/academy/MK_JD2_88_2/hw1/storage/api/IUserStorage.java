package by.it.academy.MK_JD2_88_2.hw1.storage.api;

import by.it.academy.MK_JD2_88_2.hw1.dto.User;

import java.util.List;

public interface IUserStorage {

    void add(User user);

    List<User> getAll();

    User get(String login);

    int getCount();

    void delete(String login);

}
