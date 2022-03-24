package by.it.academy.MK_JD2_88_2.hw1.controllers.web.servlets;

import by.it.academy.MK_JD2_88_2.hw1.service.MessageService;
import by.it.academy.MK_JD2_88_2.hw1.service.UserService;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IMessageService;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IUserService;
import by.it.academy.MK_JD2_88_2.hw1.service.session_counter.SessionCounterService;
import by.it.academy.MK_JD2_88_2.hw1.service.session_counter.api.ISessionCounterService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StatisticsServlet", urlPatterns = "/statistics")
public class StatisticsServlet extends HttpServlet {

    private final IUserService userService;
    private final IMessageService messageService;
    private final ISessionCounterService sessionCounter;

    public StatisticsServlet() {
        this.userService = UserService.getInstance();
        this.messageService = MessageService.getInstance();
        this.sessionCounter = SessionCounterService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        int usersCount = this.userService.getCount();
        int messagesCount = this.messageService.getCount();
        int sessionsCount = this.sessionCounter.getCounter();

        req.setAttribute("usersCount", usersCount);
        req.setAttribute("messagesCount", messagesCount);
        req.setAttribute("sessionsCount", sessionsCount);
        req.getRequestDispatcher("/views/statistics.jsp").forward(req, resp);
    }
}
