package by.it.academy.MK_JD2_88_2.hw1.controllers.web.servlets;

import by.it.academy.MK_JD2_88_2.hw1.dto.Message;
import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.service.MessageService;
import by.it.academy.MK_JD2_88_2.hw1.service.UserService;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IMessageService;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "MessageServlet", urlPatterns = "/message")
public class MessageServlet extends HttpServlet {

    private final IMessageService service;
    private final IUserService userService;
    private final String messageFilePath = "/views/message.jsp";

    public MessageServlet() {
        this.service = MessageService.getInstance();
        this.userService = UserService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(this.messageFilePath).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        User sender = (User) session.getAttribute("user");

        String senderLogin = sender.getLogin();
        String recipientLogin = req.getParameter("recipientLogin");
        String text = req.getParameter("text");

        User recipientUser = userService.getByLogin(recipientLogin);

        if (recipientUser != null) {
            Message message = Message.Builder.createBuilder()
                    .setSenderLogin(senderLogin)
                    .setRecipientLogin(recipientLogin)
                    .setText(text)
                    .setDateTime(LocalDateTime.now())
                    .setUser(sender).build();
            this.service.create(message);
        } else {
            req.setAttribute("wrongRecipientLogin", true);
        }
        req.getRequestDispatcher(this.messageFilePath).forward(req, resp);
    }
}
