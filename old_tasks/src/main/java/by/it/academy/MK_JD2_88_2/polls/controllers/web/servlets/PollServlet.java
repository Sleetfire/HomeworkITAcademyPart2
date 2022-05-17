package by.it.academy.MK_JD2_88_2.polls.controllers.web.servlets;

import by.it.academy.MK_JD2_88_2.polls.service.PollService;
import by.it.academy.MK_JD2_88_2.polls.service.api.IPollService;
import by.it.academy.MK_JD2_88_2.polls.dto.Poll;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PollServlet", urlPatterns = "/poll")
public class PollServlet extends HttpServlet {

    private IPollService service;

    public PollServlet() {
        this.service = PollService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        int artistIndex = parseStringToNumber(req.getParameter("artist")) - 1;
        String[] genres = req.getParameterValues("genre");
        if (genres.length < 3) {
            throw new IllegalArgumentException("Некорректный ввод");
        }

        int[] genresIndexes = new int[genres.length];
        for (int i = 0; i < genresIndexes.length; i++) {
            genresIndexes[i] = parseStringToNumber(genres[i]) - 1;
        }

        String about = req.getParameter("about");

        Poll poll = new Poll(artistIndex, genresIndexes, about);
        this.service.createPoll(poll);

        writer.write("<a href = \"/MK_JD2-88-2-0.0.0/best_artists_list\">Список лучших исполнителей</a><br>");
        writer.write("<a href = \"/MK_JD2-88-2-0.0.0/favourites_genres_list\">Список любимых жанров</a><br>");
        writer.write("<a href = \"/MK_JD2-88-2-0.0.0/last_short_text\">Последний краткий ответ</a>");

    }

    private int parseStringToNumber(String stringNumber) {
        int number = 0;
        try {
            number = Integer.parseInt(stringNumber);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return number;
    }
}
