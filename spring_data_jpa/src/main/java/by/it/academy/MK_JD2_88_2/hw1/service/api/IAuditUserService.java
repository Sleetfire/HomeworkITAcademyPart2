package by.it.academy.MK_JD2_88_2.hw1.service.api;

import by.it.academy.MK_JD2_88_2.hw1.model.AuditUser;
import by.it.academy.MK_JD2_88_2.hw1.model.User;

import java.util.List;

public interface IAuditUserService{

    void create(AuditUser auditUser);

    List<AuditUser> getAll();

    void deleteByUserLogin(String login);

}
