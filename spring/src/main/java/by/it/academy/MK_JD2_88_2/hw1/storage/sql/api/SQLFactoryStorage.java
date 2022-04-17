package by.it.academy.MK_JD2_88_2.hw1.storage.sql.api;

import by.it.academy.MK_JD2_88_2.hw1.storage.api.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository("sqlFactoryStorage")
public class SQLFactoryStorage implements IFactoryStorage {

    private final IUserStorage dbUserMessageAuditStorageDecorator;
    private final IMessageStorage dbMessageStorage;
    private final IAuditUserStorage dbAuditUserStorage;

    public SQLFactoryStorage(@Qualifier("dBUserMessageAuditStorageDecorator") IUserStorage dbUserMessageAuditStorageDecorator,
                             @Qualifier("dBMessageStorage") IMessageStorage dbMessageStorage,
                             @Qualifier("dBAuditUserStorage") IAuditUserStorage dbAuditUserStorage) {
        this.dbUserMessageAuditStorageDecorator = dbUserMessageAuditStorageDecorator;
        this.dbMessageStorage = dbMessageStorage;
        this.dbAuditUserStorage = dbAuditUserStorage;
    }

    @Override
    public IUserStorage getUserStorage() {
        return dbUserMessageAuditStorageDecorator;
    }

    @Override
    public IMessageStorage getMessageStorage() {
        return dbMessageStorage;
    }

    @Override
    public IAuditUserStorage getAuditUserStorage() {
        return dbAuditUserStorage;
    }

    @Override
    public boolean isSupportedType(StorageType storageType) {
        return StorageType.SQL.equals(storageType);
    }
}
