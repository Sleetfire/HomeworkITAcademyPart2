package by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api;

import by.it.academy.MK_JD2_88_2.hw1.dto.Message;
import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IMessageStorage;

import javax.persistence.EntityManager;

public interface IHibernateMessageStorage extends IMessageStorage {

    /**
     * Creating a message
     * @param message
     * @param entityManager
     */
    void add (Message message, EntityManager entityManager);

    /**
     * Deleting message by user
     * @param user
     * @param entityManager
     */
    void delete(User user, EntityManager entityManager);

}
