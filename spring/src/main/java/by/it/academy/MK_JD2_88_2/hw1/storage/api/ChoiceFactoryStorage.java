package by.it.academy.MK_JD2_88_2.hw1.storage.api;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ChoiceFactoryStorage implements IFactoryStorage {

    private IFactoryStorage factoryStorage;

    public ChoiceFactoryStorage(String type, Map<String, IFactoryStorage> factoryStorageMap) {
        this.factoryStorage = factoryStorageMap.get(type);
    }

    @Override
    public IUserStorage getUserStorage() {
        return this.factoryStorage.getUserStorage();
    }

    @Override
    public IMessageStorage getMessageStorage() {
        return this.factoryStorage.getMessageStorage();
    }

    @Override
    public IAuditUserStorage getAuditUserStorage() {
        return this.factoryStorage.getAuditUserStorage();
    }

}
