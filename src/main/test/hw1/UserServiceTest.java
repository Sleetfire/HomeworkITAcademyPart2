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
    @DisplayName(value = "Получение пользователя по логину")
    public void getByLogin() {
        createTestUser();
        Assert.assertEquals(new User("login", "password", "name", LocalDate.parse("2000-01-01")),
                this.userService.getUserByLogin("login"));
    }

    @Test
    @DisplayName(value = "Получения количества пользователей")
    public void getUserCount() {
        createTestUser();
        Assert.assertEquals(1,this.userService.getUserCount());
    }

    @Test
    @DisplayName(value = "Удаление пользователя по логину")
    public void deleteUserByLogin() {
        createTestUser();
        this.userService.deleteUserByLogin("login");
        Assert.assertEquals(0, this.userService.getUserCount());
    }

    private void createTestUser() {
        this.userService.createUser(new User("login", "password", "name",
                LocalDate.parse("2000-01-01")));
    }
}
