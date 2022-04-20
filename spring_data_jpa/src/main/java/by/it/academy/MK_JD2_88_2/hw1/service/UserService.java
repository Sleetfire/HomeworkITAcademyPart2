package by.it.academy.MK_JD2_88_2.hw1.service;

import by.it.academy.MK_JD2_88_2.hw1.model.User;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IUserService;
import by.it.academy.MK_JD2_88_2.hw1.repository.api.IUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service("userService")
@Transactional(readOnly = true)
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    private UserService(IUserRepository userRepository) {
       this.userRepository = userRepository;
    }

    @Override
    @Transactional
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
    @Transactional
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
    @Transactional
    public void update(User user, String login, LocalDateTime oldUpdate) {
        this.userRepository.update(user, login, oldUpdate);
    }
}
