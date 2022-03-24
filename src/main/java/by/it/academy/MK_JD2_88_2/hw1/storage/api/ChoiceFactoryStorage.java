package by.it.academy.MK_JD2_88_2.hw1.storage.api;

import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.HibernateFactoryStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.sql.api.SQLFactoryStorage;

public class ChoiceFactoryStorage implements IFactoryStorage {

    private static final IFactoryStorage instance = new ChoiceFactoryStorage();
    private IFactoryStorage hfs = new HibernateFactoryStorage();
    private IFactoryStorage sfs = new SQLFactoryStorage();

    private ChoiceFactoryStorage() {
    }

    @Override
    public IUserStorage getUserStorage() {
        return hfs.getUserStorage();
    }

    @Override
    public IMessageStorage getMessageStorage() {
        return hfs.getMessageStorage();
    }

    @Override
    public IAuditUserStorage getAuditUserStorage() {
        return hfs.getAuditUserStorage();
    }

    public static IFactoryStorage getInstance() {
        return instance;
    }
}
