package by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api;

import by.it.academy.MK_JD2_88_2.hw1.dto.AuditUser;
import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IAuditUserStorage;

import javax.persistence.EntityManager;

public interface IHibernateAuditStorage extends IAuditUserStorage {

    /**
     * Creating user's audit
     * @param auditUser
     * @param entityManager
     */
    void create(AuditUser auditUser, EntityManager entityManager);

    /**
     * Deleting user's audit by user
     * @param user
     * @param entityManager
     */
    void deleteByUser(User user, EntityManager entityManager);

}
