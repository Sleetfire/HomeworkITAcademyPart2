package by.it.academy.MK_JD2_88_2.hw1.controllers.web.servlets;

import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.service.UserService;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "SignInServlet", urlPatterns = "/signIn")
public class SignInServlet extends HttpServlet {

    private final IUserService service;
    private final String signInFilePath = "/views/signIn.jsp";

    public SignInServlet() {
        this.service = UserService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(this.signInFilePath).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = this.service.getUserByLogin(login);
        if (user != null) {
            String userPassword = user.getPassword();
            if (Objects.equals(userPassword, password)) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                req.getRequestDispatcher("/views/mainPage.jsp").forward(req, resp);
            } else {
                req.setAttribute("wrongPassword", true);
                req.getRequestDispatcher(this.signInFilePath).forward(req, resp);
            }
        } else {
            req.setAttribute("wrongLogin", true);
            req.getRequestDispatcher(this.signInFilePath).forward(req, resp);
        }

    }
}
