package by.it.academy.MK_JD2_88_2.polls.controllers.web.servlets;

import by.it.academy.MK_JD2_88_2.polls.service.PollService;
import by.it.academy.MK_JD2_88_2.polls.service.api.IPollService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GenresServlet", urlPatterns = "/genres")
public class GenresServlet extends HttpServlet {

    IPollService service;

    public GenresServlet() {
        this.service = PollService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write("<h2>Список жанров</h2>");
        writer.write("<ol>");
        for (String genre : this.service.getGenres()) {
            writer.write("<li>" + genre + "</li>");
        }
        writer.write("</ol>");
        writer.write("<a href = \"/MK_JD2-88-2-0.0.0/artists\">Список аристов</a>");
    }
}
