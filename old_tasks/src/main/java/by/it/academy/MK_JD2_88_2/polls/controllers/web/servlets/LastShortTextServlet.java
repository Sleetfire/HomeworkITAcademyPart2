package by.it.academy.MK_JD2_88_2.polls.controllers.web.servlets;

import by.it.academy.MK_JD2_88_2.polls.service.PollService;
import by.it.academy.MK_JD2_88_2.polls.service.api.IPollService;
import by.it.academy.MK_JD2_88_2.polls.dto.SavedPoll;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LastShortTextServlet", urlPatterns = "/last_short_text")
public class LastShortTextServlet extends HttpServlet {

    private IPollService service;

    public LastShortTextServlet() {
        this.service = PollService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        writer.write("<h3>Последние краткие ответы: от самого первого до последнего</h3>");
        writer.write("<ol>");
        for (SavedPoll pool : service.getPolls()) {
            writer.write("<li><b>О себе:</b> " + pool.getPool().getAbout() + ". <b>Время голосования:</b> " +
                    pool.getTime().toString() + ".</li>");
        }
        writer.write("</ol>");
    }
}
