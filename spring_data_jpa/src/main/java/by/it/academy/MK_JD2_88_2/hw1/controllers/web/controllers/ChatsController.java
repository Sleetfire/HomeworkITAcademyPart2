package by.it.academy.MK_JD2_88_2.hw1.controllers.web.controllers;

import by.it.academy.MK_JD2_88_2.hw1.model.Message;
import by.it.academy.MK_JD2_88_2.hw1.model.User;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IMessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/")
@SessionAttributes(value = "user")
public class ChatsController {

    private final IMessageService messageService;

    public ChatsController(IMessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(value = "/chats", method = RequestMethod.GET)
    public String index(@SessionAttribute(name = "user", required = false) User user, Model model) {

        String userLogin = user.getLogin();

        List<Message> receivedMessages = this.messageService.getByRecipientLogin(userLogin);
        List<Message> sentMessages = this.messageService.getBySenderLogin(userLogin);

        model.addAttribute("receivedMessages", receivedMessages);
        model.addAttribute("sentMessages", sentMessages);
        return "chats";
    }
}
