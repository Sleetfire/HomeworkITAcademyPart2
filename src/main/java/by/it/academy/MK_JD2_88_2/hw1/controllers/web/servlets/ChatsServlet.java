package by.it.academy.MK_JD2_88_2.hw1.controllers.web.servlets;

import by.it.academy.MK_JD2_88_2.hw1.dto.Message;
import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.service.MessageService;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IMessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ChatsServlet", urlPatterns = "/chats")
public class ChatsServlet extends HttpServlet {

    private final IMessageService service;

    public ChatsServlet() {
        this.service = MessageService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user =(User) session.getAttribute("user");
        String userLogin = user.getLogin();

        List<Message> receivedMessages = this.service.getByRecipientLogin(userLogin);
        List<Message> sentMessages = this.service.getBySenderLogin(userLogin);

        req.setAttribute("receivedMessages", receivedMessages);
        req.setAttribute("sentMessages", sentMessages);
        req.getRequestDispatcher("/views/chats.jsp").forward(req, resp);
    }
}
