package by.it.academy.MK_JD2_88_2.polls.controllers.web.servlets;

import by.it.academy.MK_JD2_88_2.polls.service.PollService;
import by.it.academy.MK_JD2_88_2.polls.service.api.IPollService;
import by.it.academy.MK_JD2_88_2.polls.dto.ChoiceWithCounter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "FavouriteGenresServlet", urlPatterns = "/favourites_genres_list")
public class FavouriteGenresServlet extends HttpServlet {

    private IPollService service;

    public FavouriteGenresServlet() {
        this.service = PollService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        List<ChoiceWithCounter<String>> sorted = this.service.getGenreTop();

        writer.write("<h3>Список самых любимых жанров</h3>");
        writer.write("<ol>");
        for (ChoiceWithCounter<String> choice : sorted) {
            writer.write("<li>" + choice.getChoice() + " набрал " + choice.getCountPoll() + "</li>");
        }
        writer.write("</ol>");

    }
}
