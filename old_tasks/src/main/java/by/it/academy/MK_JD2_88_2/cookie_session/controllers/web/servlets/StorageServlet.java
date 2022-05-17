package by.it.academy.MK_JD2_88_2.cookie_session.controllers.web.servlets;

import by.it.academy.MK_JD2_88_2.cookie_session.dto.Person;
import by.it.academy.MK_JD2_88_2.cookie_session.service.CookieService;
import by.it.academy.MK_JD2_88_2.cookie_session.service.SessionService;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@WebServlet(name = "StorageServlet", urlPatterns = "/storage")
public class StorageServlet extends HttpServlet {

    private static final String FIRST_NAME_PARAM_KEY = "firstName";
    private static final String LAST_NAME_PARAM_KEY = "lastName";
    private static final String AGE_PARAM_KEY = "age";

    private static final String HEADER_KEY = "storage-variant";
    private static final String COOKIE_KEY = "cookie";
    private static final String SESSION_KEY = "session";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        String firstName = req.getParameter(FIRST_NAME_PARAM_KEY);
        String lastName = req.getParameter(LAST_NAME_PARAM_KEY);
        String stringAge = req.getParameter(AGE_PARAM_KEY);

        int age = NumberUtils.isDigits(stringAge) ? Integer.parseInt(stringAge) : 0;

        String header = req.getHeader(HEADER_KEY);

        Person person = new Person(firstName, lastName, age);

        if (Objects.equals(header, COOKIE_KEY)) {
            CookieService.getInstance().savePersonInCookie(person, resp);
            writer.write("<span style = \"color: red\">" + CookieService.getInstance().getPersonFromCookie(req)
                    .toString() + "</span");
        } else if (Objects.equals(header, SESSION_KEY)) {
            SessionService.getInstance().savePersonInSession(person, req);
            writer.write("<span style = \"color: blue\">" + SessionService.getInstance().getPersonFromSession(req)
                    .toString() + "</span");
        } else {
            writer.write("Не указано хранилище!<br>");
            writer.write("<span style = \"color: green\">" + person + "</span");
        }

    }

}
