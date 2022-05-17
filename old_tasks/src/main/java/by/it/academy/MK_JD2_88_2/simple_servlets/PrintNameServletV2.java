package by.it.academy.MK_JD2_88_2.simple_servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PrintNameServletV2", urlPatterns = "/printNameV2")
public class PrintNameServletV2 extends HttpServlet {

    private String NAME_PARAMETER = "name";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();
        String[] names = req.getParameterMap().get(this.NAME_PARAMETER);

        if (names != null) {
            for (String name : names) {
                writer.write("<p>" + this.NAME_PARAMETER + ": " + name + "</p>");
            }
        }
    }
}
