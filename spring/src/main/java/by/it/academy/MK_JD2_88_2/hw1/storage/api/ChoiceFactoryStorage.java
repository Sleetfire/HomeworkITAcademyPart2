package by.it.academy.MK_JD2_88_2.hw1.storage.api;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChoiceFactoryStorage {

    private List<IFactoryStorage> factoryStorages;
    private StorageType selectedStorageType;

    public ChoiceFactoryStorage(List<IFactoryStorage> factoryStorages, String storageType) {
        this.factoryStorages = factoryStorages;
        this.selectedStorageType = StorageType.valueOf(storageType);
    }

    public IUserStorage getUserStorage() {
        return getFactoryStorage(this.selectedStorageType).getUserStorage();
    }

    public IMessageStorage getMessageStorage() {
        return getFactoryStorage(this.selectedStorageType).getMessageStorage();
    }

    public IAuditUserStorage getAuditUserStorage () {
        return getFactoryStorage(this.selectedStorageType).getAuditUserStorage();
    }

    private IFactoryStorage getFactoryStorage(StorageType storageType) {
        for (IFactoryStorage storage : this.factoryStorages) {
            if (storage.isSupportedType(storageType)) {
                return storage;
            }
        }
        return null;
    }
}
