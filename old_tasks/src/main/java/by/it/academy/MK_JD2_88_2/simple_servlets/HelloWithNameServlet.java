package by.it.academy.MK_JD2_88_2.simple_servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HelloWithNameServlet", urlPatterns = "/hello_with_name")
public class HelloWithNameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        writer.write("<p><span style='color: blue;'>Hello, " + lastName + " " + firstName + "!</span></p>");
    }
}
