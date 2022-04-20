package by.it.academy.MK_JD2_88_2.hw1.controllers.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/leave")
@SessionAttributes(value = "user")
public class LeaveController {

    @RequestMapping(name = "/", method = RequestMethod.GET)
    public String index(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/main";
    }
}
