package by.it.academy.MK_JD2_88_2.simple_servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PrintServletV3", urlPatterns = "/printNameV3")
public class PrintNameServletV3 extends HttpServlet {

    private String NAME_PARAMETER_HEADER = "ARRAY_NAME_PARAM";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        String header = req.getHeader(this.NAME_PARAMETER_HEADER);

        String[] names = req.getParameterMap().get(header);
        if (names != null) {
            for (String name : names) {
                writer.write("<p>" + header + ": " + name + "</p>");
            }
        }
    }
}
