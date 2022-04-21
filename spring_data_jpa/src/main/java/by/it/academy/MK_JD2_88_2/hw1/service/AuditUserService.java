package by.it.academy.MK_JD2_88_2.hw1.service;

import by.it.academy.MK_JD2_88_2.hw1.model.AuditUser;
import by.it.academy.MK_JD2_88_2.hw1.repository.api.IAuditUserRepository;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IAuditUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AuditUserService implements IAuditUserService {

    private IAuditUserRepository auditUserRepository;

    public AuditUserService(IAuditUserRepository auditUserRepository) {
        this.auditUserRepository = auditUserRepository;
    }

    @Override
    @Transactional
    public void create(AuditUser auditUser) {
        this.auditUserRepository.save(auditUser);
    }

    @Override
    public List<AuditUser> getAll() {
        return this.auditUserRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteByUserLogin(String login) {
        this.auditUserRepository.deleteByUserLogin(login);
    }
}
