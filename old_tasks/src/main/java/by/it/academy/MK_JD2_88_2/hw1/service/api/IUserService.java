package by.it.academy.MK_JD2_88_2.hw1.service.api;

import by.it.academy.MK_JD2_88_2.hw1.dto.User;

import java.util.List;

public interface IUserService {

    void createUser(User user);

    List<User> getUsers();

    User getUserByLogin(String login);

    int getUserCount();

    void deleteUserByLogin(String login);

}
