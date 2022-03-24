package by.it.academy.MK_JD2_88_2.hw1.storage.sql.api;

import by.it.academy.MK_JD2_88_2.hw1.storage.api.IAuditUserStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IFactoryStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IMessageStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IUserStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.sql.DBAuditUserStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.sql.DBMessageStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.sql.DBUserMessageAuditStorageDecorator;

public class SQLFactoryStorage implements IFactoryStorage {
    @Override
    public IUserStorage getUserStorage() {
        return DBUserMessageAuditStorageDecorator.getInstance();
    }

    @Override
    public IMessageStorage getMessageStorage() {
        return DBMessageStorage.getInstance();
    }

    @Override
    public IAuditUserStorage getAuditUserStorage() {
        return DBAuditUserStorage.getInstance();
    }
}
