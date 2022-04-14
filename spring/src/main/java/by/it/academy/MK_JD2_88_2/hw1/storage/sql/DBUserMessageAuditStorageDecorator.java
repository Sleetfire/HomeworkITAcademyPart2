package by.it.academy.MK_JD2_88_2.hw1.storage.sql;

import by.it.academy.MK_JD2_88_2.hw1.dto.AuditUser;
import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IAuditUserStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IMessageStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IUserStorage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository("dBUserMessageAuditStorageDecorator")
public class DBUserMessageAuditStorageDecorator implements IUserStorage {

    private final IUserStorage userStorage;
    private final IMessageStorage messageStorage;
    private final IAuditUserStorage auditUserStorage;

    public DBUserMessageAuditStorageDecorator(@Qualifier("dBUserStorage") IUserStorage dbUserStorage,
                                              @Qualifier("dBMessageStorage") IMessageStorage dbMessageStorage,
                                              @Qualifier("dBAuditUserStorage") IAuditUserStorage dbAuditUserStorage) {
        this.userStorage = dbUserStorage;
        this.messageStorage = dbMessageStorage;
        this.auditUserStorage = dbAuditUserStorage;
    }

    @Override
    public void create(User user) {
        this.userStorage.create(user);
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
        this.auditUserStorage.deleteByUser(get(login));
        this.messageStorage.delete(login);
        this.userStorage.delete(login);
    }

    @Override
    public void update(User user, String login) {
        this.userStorage.update(user, login);
    }
}
