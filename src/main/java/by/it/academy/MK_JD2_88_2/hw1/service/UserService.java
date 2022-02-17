package by.it.academy.MK_JD2_88_2.hw1.service;

import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IUserService;

import java.util.*;

public class UserService implements IUserService {

    private final Map<String, User> userMap = new HashMap<>();
    private static final IUserService instance = new UserService();

    private UserService() {
    }

    @Override
    public void createUser(User user) {
        String login = user.getLogin();
        this.userMap.put(login, user);
    }

    @Override
    public List<User> getUsers() {
        return List.copyOf(this.userMap.values());
    }

    @Override
    public User getUserByLogin(String login) {
        return this.userMap.get(login);
    }

    @Override
    public int getUserCount() {
        return getUsers().size();
    }

    @Override
    public void deleteUserByLogin(String login) {
        this.userMap.remove(login);
    }

    public static IUserService getInstance() {
        return instance;
    }
}
