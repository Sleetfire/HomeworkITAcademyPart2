package by.it.academy.MK_JD2_88_2.hw1.storage.sql;

import by.it.academy.MK_JD2_88_2.hw1.dto.AuditUser;
import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IAuditUserStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IMessageStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IUserStorage;

import java.time.LocalDateTime;
import java.util.List;

public class DBUserMessageAuditStorageDecorator implements IUserStorage {

    private final IUserStorage userStorage = DBUserStorage.getInstance();
    private final IMessageStorage messageStorage = DBMessageStorage.getInstance();
    private final IAuditUserStorage auditUserStorage = DBAuditUserStorage.getInstance();
    private static final IUserStorage instance = new DBUserMessageAuditStorageDecorator();

    private DBUserMessageAuditStorageDecorator() {

    }

    @Override
    public void add(User user) {
        this.userStorage.add(user);
        AuditUser audit = AuditUser.Builder.createBuilder()
                .setDtCreate(LocalDateTime.now())
                .setText("Registration")
                .setAuthor(null)
                .setUser(user)
                .build();
        this.auditUserStorage.create(audit);
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
        this.userStorage.delete(login);
        this.auditUserStorage.deleteByUser(this.userStorage.get(login));
        this.messageStorage.delete(login);
    }

    public static IUserStorage getInstance() {
        return instance;
    }
}
