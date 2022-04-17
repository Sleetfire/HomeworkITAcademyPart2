package by.it.academy.MK_JD2_88_2.hw1.service;

import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IUserService;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.ChoiceFactoryStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IUserStorage;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("userService")
public class UserService implements IUserService {

    private final IUserStorage choiceFactoryStorage;

    private UserService(ChoiceFactoryStorage choiceFactoryStorage) {
        this.choiceFactoryStorage = choiceFactoryStorage.getUserStorage();
    }

    @Override
    public void create(User user) {
        this.choiceFactoryStorage.create(user);
    }

    @Override
    public List<User> getAll() {
        return this.choiceFactoryStorage.getAll();
    }

    @Override
    public User getByLogin(String login) {
        return this.choiceFactoryStorage.get(login);
    }

    @Override
    public int getCount() {
        return this.choiceFactoryStorage.getCount();
    }

    @Override
    public void deleteByLogin(String login) {
        this.choiceFactoryStorage.delete(login);
    }

    @Override
    public boolean checkPassword(String login, String password) {
        User user = this.getByLogin(login);
        if (isExist(login)) {
            String userPassword = user.getPassword();
            return Objects.equals(userPassword, password);
        } else {
            return false;
        }
    }

    @Override
    public boolean isExist(String login) {
        return getByLogin(login) != null;
    }

    @Override
    public void update(User user, String login) {
        this.choiceFactoryStorage.update(user, login);
    }
}
