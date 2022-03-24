package by.it.academy.MK_JD2_88_2.hw1.storage.api;

import by.it.academy.MK_JD2_88_2.hw1.dto.AuditUser;
import by.it.academy.MK_JD2_88_2.hw1.dto.Pageable;
import by.it.academy.MK_JD2_88_2.hw1.dto.User;


import java.util.List;

public interface IAuditUserStorage {

    void create(AuditUser audit);

    List<AuditUser> read(Pageable pageable);

    void deleteByUser(User user);

}
