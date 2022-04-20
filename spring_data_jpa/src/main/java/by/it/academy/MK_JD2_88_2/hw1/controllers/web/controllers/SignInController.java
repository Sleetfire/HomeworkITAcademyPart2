package by.it.academy.MK_JD2_88_2.hw1.controllers.web.controllers;

import by.it.academy.MK_JD2_88_2.hw1.service.api.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/signIn")
@SessionAttributes(value = "user")
public class SignInController {

    private final IUserService userService;

    public SignInController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(name = "/", method = RequestMethod.GET)
    public String index() {
        return "signIn";
    }

    @RequestMapping(name = "/", method = RequestMethod.POST)
    public String create(@RequestParam(name = "login") String login, @RequestParam(name = "password") String password,
                         Model model) {
        if (this.userService.isExist(login)) {
            if (this.userService.checkPassword(login, password)) {
                model.addAttribute("user", userService.getByLogin(login));
                return "redirect:/main";
            } else {
                model.addAttribute("wrongPassword", true);
                return "signIn";
            }
        } else {
            model.addAttribute("wrongLogin", true);
            return "signIn";
        }
    }
}
