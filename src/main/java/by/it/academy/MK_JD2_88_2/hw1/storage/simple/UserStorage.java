package by.it.academy.MK_JD2_88_2.hw1.storage.simple;

import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IUserStorage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserStorage implements IUserStorage {

    private final Map<String, User> userMap = new HashMap<>();
    private static final IUserStorage instance = new UserStorage();

    private UserStorage() {

    }

    @Override
    public void add(User user) {
        String login = user.getLogin();
        this.userMap.put(login, user);
    }

    @Override
    public List<User> getAll() {
        return List.copyOf(this.userMap.values());
    }

    @Override
    public User get(String login) {
        return this.userMap.get(login);
    }

    @Override
    public int getCount() {
        return getAll().size();
    }

    @Override
    public void delete(String login) {
        this.userMap.remove(login);
    }

    public static IUserStorage getInstance() {
        return instance;
    }
}
