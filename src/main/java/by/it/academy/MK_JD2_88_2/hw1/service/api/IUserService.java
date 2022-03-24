package by.it.academy.MK_JD2_88_2.hw1.service.api;

import by.it.academy.MK_JD2_88_2.hw1.dto.User;

import java.util.List;

public interface IUserService {

    void create(User user);

    List<User> getAll();

    User getByLogin(String login);

    int getCount();

    void deleteByLogin(String login);

    boolean checkPassword(String login, String password);

    boolean isExist(String login);

}
