package by.it.academy.MK_JD2_88_2.hw1.controllers.web.controllers;

import by.it.academy.MK_JD2_88_2.hw1.model.User;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IUserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Objects;


@Controller
@RequestMapping("/profile")
@SessionAttributes(types = User.class)
public class ProfileController {

    private final IUserService userService;

    public ProfileController(@Qualifier("userDecoratorService") IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(name = "/", method = RequestMethod.GET)
    public String index(@SessionAttribute(name = "user", required = false) User user, Model model){
        if (user != null) {
            model.addAttribute("user", user);
           return "profile";
        }
        return "mainPage";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String indexUpdate() {
        return "update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public String update(@SessionAttribute(name = "user", required = false) User user, Model model,
                         @RequestBody(required = false) MultiValueMap<String, String> formParams) {
        LocalDate birthday = LocalDate.parse(Objects.requireNonNull(formParams.getFirst("birthday")));
        User updateUser = User.Builder.createBuilder()
                .setLogin(formParams.getFirst("login"))
                .setPassword(formParams.getFirst("password"))
                .setName(formParams.getFirst("name"))
                .setBirthday(birthday)
                .setRgDate(user.getRgDate())
                .setUpdateDateTime(user.getUpdateDateTime())
                .build();
        this.userService.update(updateUser, user.getLogin(), user.getUpdateDateTime());
        model.addAttribute(this.userService.getByLogin(updateUser.getLogin()));
        return "redirect:/profile";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@SessionAttribute(name = "user", required = false) User user){
        String login = user.getLogin();
        this.userService.deleteByLogin(login);
        return "redirect:/leave";
    }
}
