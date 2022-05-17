package by.it.academy.MK_JD2_88_2.session_counter.controllers.web.servlets;

import by.it.academy.MK_JD2_88_2.session_counter.service.SessionCounter;
import by.it.academy.MK_JD2_88_2.session_counter.service.api.ISessionCounter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ActiveSessionsServlet", urlPatterns = "/active_sessions")
public class ActiveSessionsServlet extends HttpServlet {

    private ISessionCounter counter;

    public ActiveSessionsServlet() {
        this.counter = SessionCounter.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        writer.write("<b> Число активных сессий: </b>" + this.counter.getCounter().get());
    }
}
