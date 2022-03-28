package by.it.academy.MK_JD2_88_2.hw1.storage.api;

import by.it.academy.MK_JD2_88_2.hw1.dto.User;

import java.util.List;

public interface IUserStorage {

    /**
     * Creating a user
     * @param user
     */
    void add(User user);

    /**
     * Getting all users
     * @return list of all users
     */
    List<User> getAll();

    /**
     * Getting user by login
     * @param login
     * @return user
     */
    User get(String login);

    /**
     * Getting user's count
     * @return count
     */
    int getCount();

    /**
     * Deleting user by login
     * @param login
     */
    void delete(String login);

}
