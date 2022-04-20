package by.it.academy.MK_JD2_88_2.hw1.service;

import by.it.academy.MK_JD2_88_2.hw1.model.AuditUser;
import by.it.academy.MK_JD2_88_2.hw1.model.User;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IAuditUserService;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IMessageService;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserDecoratorService implements IUserService {

    private IUserService userService;
    private IMessageService messageService;
    private IAuditUserService auditUserService;

    public UserDecoratorService(IUserService userService, IMessageService messageService, IAuditUserService auditUserService) {
        this.userService = userService;
        this.messageService = messageService;
        this.auditUserService = auditUserService;
    }

    @Override
    @Transactional
    public void create(User user) {
        this.userService.create(user);
        AuditUser auditUser = AuditUser.Builder.createBuilder()
                .setUser(user)
                .setAuthor(user)
                .setDtCreate(LocalDateTime.now())
                .setText("Регистрация")
                .build();
        this.auditUserService.create(auditUser);
    }

    @Override
    public List<User> getAll() {
        return this.userService.getAll();
    }

    @Override
    public User getByLogin(String login) {
        return this.userService.getByLogin(login);
    }

    @Override
    public int getCount() {
        return this.userService.getCount();
    }

    @Override
    @Transactional
    public void deleteByLogin(String login) {
        this.messageService.deleteByUserLogin(login);
        User user = this.userService.getByLogin(login);
        this.auditUserService.deleteByUser(user);
        this.userService.deleteByLogin(login);
    }

    @Override
    public boolean checkPassword(String login, String password) {
        return this.userService.checkPassword(login, password);
    }

    @Override
    public boolean isExist(String login) {
        return this.userService.isExist(login);
    }

    @Override
    public void update(User user, String login, LocalDateTime oldUpdate) {
        this.userService.update(user, login, oldUpdate);
    }
}
