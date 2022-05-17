package by.it.academy.MK_JD2_88_2.simple_servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(name = "PrintNameServletV1", urlPatterns = "/printNameV1")
public class PrintNameServletV1 extends HttpServlet {

    private String NAME_PARAMETER_PREFIX = "name_";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();
        Enumeration<String> paramsName = req.getParameterNames();

        while (paramsName.hasMoreElements()) {
            String paramName = paramsName.nextElement();
            if (paramName.startsWith(this.NAME_PARAMETER_PREFIX)) {
                writer.write("<p>" + paramName + ": " + req.getParameter(paramName) + "</p>");
            }
        }
    }
}
