package by.it.academy.MK_JD2_88_2.hw1.service.api;

import by.it.academy.MK_JD2_88_2.hw1.dto.User;

import java.util.List;

public interface IUserService {

    /**
     * Creating a user
     * @param user
     */
    void create(User user);

    /**
     * Getting all users
     * @return list of users
     */
    List<User> getAll();

    /**
     * Getting user by login
     * @param login
     * @return user
     */
    User getByLogin(String login);

    /**
     * Getting count of users
     * @return count
     */
    int getCount();

    /**
     * Deleting user by login
     * @param login
     */
    void deleteByLogin(String login);

    /**
     * Checking user's password
     * @param login user's login
     * @param password user's password
     * @return true if password is correct
     */
    boolean checkPassword(String login, String password);

    /**
     * Checking whether the user exists
     * @param login user's login
     * @return true if user exists
     */
    boolean isExist(String login);

}
