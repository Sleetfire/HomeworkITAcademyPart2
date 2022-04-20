package by.it.academy.MK_JD2_88_2.hw1.controllers.web.controllers;

import by.it.academy.MK_JD2_88_2.hw1.model.Message;
import by.it.academy.MK_JD2_88_2.hw1.model.User;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IMessageService;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IUserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/message")
public class MessageController{

    private final IMessageService messageService;
    private final IUserService userService;

    public MessageController(IMessageService messageService, @Qualifier("userDecoratorService") IUserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @RequestMapping(name = "/", method = RequestMethod.GET)
    public String index(){
        return "message";
    }

    @RequestMapping(name = "/", method = RequestMethod.POST)
    public String create(@SessionAttribute(name = "user", required = false) User user,
                          @RequestParam(name = "recipientLogin") String recipientLogin,
                          @RequestParam(name = "text") String text,
                          Model model){

        User recipient = userService.getByLogin(recipientLogin);

        if (recipient != null) {
            Message message = Message.Builder.createBuilder()
                    .setText(text)
                    .setDateTime(LocalDateTime.now())
                    .setSender(user)
                    .setRecipient(recipient)
                    .build();
            this.messageService.create(message);
        } else {
            model.addAttribute("wrongRecipientLogin", true);
        }
        return "message";
    }
}
