package by.it.academy.MK_JD2_88_2.hw1.storage.hibernate;

import by.it.academy.MK_JD2_88_2.hw1.dto.AuditUser;
import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IUserStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.HibernateDBInitializer;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.IHibernateAuditStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.IHibernateMessageStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.IHibernateUserStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.adapter.UserAdapter;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.util.List;

public class HibernateUserMessageAuditDecorator implements IUserStorage {

    private final IHibernateUserStorage userStorage;
    private final IHibernateMessageStorage messageStorage;
    private final IHibernateAuditStorage auditUserStorage;
    private final HibernateDBInitializer hibernateDBInitializer;
    private static final IUserStorage instance = new HibernateUserMessageAuditDecorator();

    private HibernateUserMessageAuditDecorator() {
        this.userStorage = HibernateUserStorage.getInstance();
        this.messageStorage = HibernateMessageStorage.getInstance();
        this.auditUserStorage = HibernateAuditUserStorage.getInstance();
        this.hibernateDBInitializer = HibernateDBInitializer.getInstance();
    }

    @Override
    public void add(User user) {
        EntityManager entityManager = this.hibernateDBInitializer.getManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Long userId = this.userStorage.add(user, entityManager);
            user.setId(userId);
            AuditUser auditUser = AuditUser.Builder.createBuilder()
                    .setUser(user)
                    .setAuthor(null)
                    .setText("Registration")
                    .setDtCreate(LocalDateTime.now())
                    .build();
            this.auditUserStorage.create(auditUser, entityManager);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Ошибка выполнения транзакции", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<User> getAll() {
        return this.userStorage.getAll();
    }

    @Override
    public User get(String login) {
        return this.userStorage.get(login);
    }

    @Override
    public int getCount() {
        return this.userStorage.getCount();
    }

    @Override
    public void delete(String login) {
        EntityManager entityManager = this.hibernateDBInitializer.getManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            this.auditUserStorage.deleteByUser(get(login), entityManager);
            this.messageStorage.delete(get(login), entityManager);
            this.userStorage.delete(login, entityManager);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Ошибка выполнения транзакции", e);
        } finally {
            entityManager.close();
        }
    }

    public static IUserStorage getInstance() {
        return instance;
    }
}
