package by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api;

import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IUserStorage;

import javax.persistence.EntityManager;

public interface IHibernateUserStorage extends IUserStorage {

    /**
     * Creating a user
     * @param user
     * @param entityManager
     */
    Long add (User user, EntityManager entityManager);

    /**
     * Deleting a user by login
     * @param login
     * @param entityManager
     */
    void delete(String login, EntityManager entityManager);

}
