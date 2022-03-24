package by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api;

import by.it.academy.MK_JD2_88_2.hw1.storage.api.IAuditUserStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IFactoryStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IMessageStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IUserStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.HibernateAuditUserStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.HibernateMessageStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.HibernateUserMessageAuditDecorator;

public class HibernateFactoryStorage implements IFactoryStorage {
    @Override
    public IUserStorage getUserStorage() {
        return HibernateUserMessageAuditDecorator.getInstance();
    }

    @Override
    public IMessageStorage getMessageStorage() {
        return HibernateMessageStorage.getInstance();
    }

    @Override
    public IAuditUserStorage getAuditUserStorage() {
        return HibernateAuditUserStorage.getInstance();
    }
}
