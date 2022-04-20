package by.it.academy.MK_JD2_88_2.hw1.controllers.web.controllers;

import by.it.academy.MK_JD2_88_2.hw1.service.api.IMessageService;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IUserService;
import by.it.academy.MK_JD2_88_2.hw1.service.session_counter.api.ISessionCounterService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {

    private final IUserService userService;
    private final IMessageService messageService;
    private final ISessionCounterService sessionCounter;

    public StatisticsController(@Qualifier("userDecoratorService") IUserService userService, IMessageService messageService,
                                ISessionCounterService sessionCounter) {
        this.userService = userService;
        this.messageService = messageService;
        this.sessionCounter = sessionCounter;
    }

    @RequestMapping(name = "/", method = RequestMethod.GET)
    public String index(Model model) {
        int usersCount = this.userService.getCount();
        int messagesCount = this.messageService.getCount();
        int sessionsCount = this.sessionCounter.getCounter();

        model.addAttribute("usersCount", usersCount);
        model.addAttribute("messagesCount", messagesCount);
        model.addAttribute("sessionsCount", sessionsCount);
       return "statistics";
    }
}
