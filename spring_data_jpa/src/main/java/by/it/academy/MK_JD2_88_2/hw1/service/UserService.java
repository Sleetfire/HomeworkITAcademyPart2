package by.it.academy.MK_JD2_88_2.hw1.service;

import by.it.academy.MK_JD2_88_2.hw1.model.User;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IUserService;
import by.it.academy.MK_JD2_88_2.hw1.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service("userService")
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    private UserService(IUserRepository userRepository) {
       this.userRepository = userRepository;
    }

    @Override
    public void create(User user) {
        this.userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User getByLogin(String login) {
        return this.userRepository.findByLogin(login);
    }

    @Override
    public int getCount() {
        return Math.toIntExact(this.userRepository.count());
    }

    @Override
    public void deleteByLogin(String login) {
        this.userRepository.deleteByLogin(login);
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
    public void update(User user, String login, LocalDateTime oldUpdate) {
        this.userRepository.update(user, login, oldUpdate);
    }
}
