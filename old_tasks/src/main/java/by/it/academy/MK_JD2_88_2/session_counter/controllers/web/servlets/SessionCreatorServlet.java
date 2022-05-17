package by.it.academy.MK_JD2_88_2.session_counter.controllers.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SessionCreatorServlet", urlPatterns = "/create_session")
public class SessionCreatorServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        String sessionName = req.getParameter("sessionName");
        String sessionObject = req.getParameter("sessionObject");

        HttpSession session = req.getSession();
        session.setAttribute(sessionName, sessionObject);

        writer.write("Created new session object: " + session + " ----- " + sessionObject);
        writer.write("<h3>Session parameters: </h3>");
        writer.write("Session's id: " + session.getId());
        writer.write("Session's creation time: " + session.getCreationTime());

    }
}
