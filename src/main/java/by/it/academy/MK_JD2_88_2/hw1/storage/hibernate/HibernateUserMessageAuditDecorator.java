package by.it.academy.MK_JD2_88_2.hw1.storage.hibernate;

import by.it.academy.MK_JD2_88_2.hw1.dto.AuditUser;
import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IAuditUserStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IMessageStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IUserStorage;

import java.time.LocalDateTime;
import java.util.List;

public class HibernateUserMessageAuditDecorator implements IUserStorage {

    private final IUserStorage userStorage = HibernateUserStorage.getInstance();
    private final IMessageStorage messageStorage = HibernateMessageStorage.getInstance();
    private final IAuditUserStorage auditUserStorage = HibernateAuditUserStorage.getInstance();
    private static IUserStorage instance = new HibernateUserMessageAuditDecorator();

    private HibernateUserMessageAuditDecorator() {

    }

    @Override
    public void add(User user) {
        this.userStorage.add(user);
        AuditUser auditUser = AuditUser.Builder.createBuilder()
                .setUser(this.userStorage.get(user.getLogin()))
                .setAuthor(null)
                .setText("Registration")
                .setDtCreate(LocalDateTime.now()).build();
        this.auditUserStorage.create(auditUser);
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
        this.auditUserStorage.deleteByUser(get(login));
        this.messageStorage.delete(login);
        this.userStorage.delete(login);
    }

    public static IUserStorage getInstance() {
        return instance;
    }
}
