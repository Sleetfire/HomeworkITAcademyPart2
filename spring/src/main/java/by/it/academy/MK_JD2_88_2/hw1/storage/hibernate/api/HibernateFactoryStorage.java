package by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api;

import by.it.academy.MK_JD2_88_2.hw1.storage.api.*;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateFactoryStorage implements IFactoryStorage {

    private IHibernateUserStorage hibernateUserMessageAuditStorage;
    private IHibernateMessageStorage hibernateMessageStorage;
    private IHibernateAuditStorage hibernateAuditStorage;

    public HibernateFactoryStorage(IHibernateUserStorage hibernateUserMessageAuditStorage,
                                   IHibernateMessageStorage hibernateMessageStorage,
                                   IHibernateAuditStorage hibernateAuditStorage) {
        this.hibernateUserMessageAuditStorage = hibernateUserMessageAuditStorage;
        this.hibernateMessageStorage = hibernateMessageStorage;
        this.hibernateAuditStorage = hibernateAuditStorage;
    }

    @Override
    public IUserStorage getUserStorage() {
        return hibernateUserMessageAuditStorage;
    }

    @Override
    public IMessageStorage getMessageStorage() {
        return hibernateMessageStorage;
    }

    @Override
    public IAuditUserStorage getAuditUserStorage() {
        return hibernateAuditStorage;
    }

    @Override
    public boolean isSupportedType(StorageType storageType) {
        return StorageType.HIBERNATE.equals(storageType);
    }
}
