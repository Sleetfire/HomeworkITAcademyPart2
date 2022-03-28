package by.it.academy.MK_JD2_88_2.hw1.storage.api;

import by.it.academy.MK_JD2_88_2.hw1.dto.AuditUser;
import by.it.academy.MK_JD2_88_2.hw1.dto.Pageable;
import by.it.academy.MK_JD2_88_2.hw1.dto.User;


import java.util.List;

public interface IAuditUserStorage {

    /**
     * Creating an audit
     * @param audit
     */
    void create(AuditUser audit);

    /**
     * Reading audits
     * @param pageable
     * @return list of audits
     */
    List<AuditUser> read(Pageable pageable);

    /**
     * Deleting audit by user
     * @param user
     */
    void deleteByUser(User user);

}
