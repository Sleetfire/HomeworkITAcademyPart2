package by.it.academy.MK_JD2_88_2.hw1.controllers.web.controllers;

import by.it.academy.MK_JD2_88_2.hw1.model.User;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/signUp")
public class SignUpController {

    private final IUserService userService;
    public SignUpController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(name = "/", method = RequestMethod.GET)
    public String index() {
        return "signUp";
    }

    @RequestMapping(name = "/", method = RequestMethod.POST)
    public String create(@RequestParam(name = "login") String login, @RequestParam(name = "password") String password,
                         @RequestParam(name = "name") String name, @RequestParam(name = "birthday") String birthday,
                         Model model) {

        if (login.isEmpty() || password.isEmpty() || name.isEmpty() || birthday.isEmpty()) {
            model.addAttribute("emptyFormField", true);
            return "signUp";
        }
        if (this.userService.isExist(login)) {
            model.addAttribute("userCreated", true);
            return "signUp";
        } else {
            LocalDate birthdayDate = LocalDate.parse(birthday);
            User user = User.Builder.createBuilder()
                    .setLogin(login)
                    .setPassword(password)
                    .setName(name)
                    .setRgDate(LocalDate.now())
                    .setBirthday(birthdayDate)
                    .setUpdateDateTime(LocalDateTime.now())
                    .build();
            this.userService.create(user);
            return "redirect:/main";
        }
    }
}
