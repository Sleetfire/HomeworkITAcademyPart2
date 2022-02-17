package by.it.academy.MK_JD2_88_2.hw1.controllers.web.servlets;

import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.service.UserService;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "SignUpServlet", urlPatterns = "/signUp")
public class SignUpServlet extends HttpServlet {

    private final IUserService service;
    private final String signUpPageUrl = "/views/signUp.jsp";

    public SignUpServlet() {
        this.service = UserService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(this.signUpPageUrl).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String strBirthday = req.getParameter("birthday");

        if (login.isEmpty() || password.isEmpty() || name.isEmpty() || strBirthday.isEmpty()) {
            req.setAttribute("emptyFormField", true);
            req.getRequestDispatcher(this.signUpPageUrl).forward(req, resp);
        }
        if (isUserCreated(login)) {
            req.setAttribute("userCreated", true);
            req.getRequestDispatcher(this.signUpPageUrl).forward(req, resp);
        } else {
            LocalDate birthday = LocalDate.parse(strBirthday);
            User user = new User(login, password, name, birthday);
            this.service.createUser(user);
            req.getRequestDispatcher("/views/mainPage.jsp").forward(req, resp);
        }
    }

    private boolean isUserCreated(String login) {
        User user = this.service.getUserByLogin(login);
        return user != null;
    }
}
