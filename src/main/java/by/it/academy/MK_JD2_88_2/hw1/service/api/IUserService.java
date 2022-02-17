package by.it.academy.MK_JD2_88_2.hw1.service.api;

import by.it.academy.MK_JD2_88_2.hw1.dto.User;

import java.util.List;

public interface IUserService {

    /**
     * Adds user in the storage (List or Map)
     * @param user user object
     */
    void createUser(User user);

    /**
     * Returns the list of all users from storage
     * @return the list of all users
     */
    List<User> getUsers();

    /**
     * Returns user from storage by login
     * @param login user's login
     * @return
     */
    User getUserByLogin(String login);

    /**
     * Returns the number of  the users
     * @return number of users
     */
    int getUserCount();

    /**
     * Deletes user from storage
     * @param login user's login
     */
    void deleteUserByLogin(String login);
}
