package by.it.academy.MK_JD2_88_2.hw1.storage.api;

public interface IFactoryStorage {

    IUserStorage getUserStorage();

    IMessageStorage getMessageStorage();

    IAuditUserStorage getAuditUserStorage();


}
