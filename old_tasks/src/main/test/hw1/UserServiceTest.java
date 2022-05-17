package hw1;

import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.service.UserService;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDate;


public class UserServiceTest {

    private final IUserService userService = UserService.getInstance();

    @Test
    @DisplayName("Получение пользователя по логину")
    public void getByLogin() {
        this.userService.createUser(new User("login", "password", "name",
                LocalDate.parse("2000-01-01")));
        Assert.assertEquals(new User("login", "password", "name", LocalDate.parse("2000-01-01")),
                this.userService.getUserByLogin("login"));
    }
}
