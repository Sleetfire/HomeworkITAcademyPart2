package by.it.academy.MK_JD2_88_2.cw1.controllers.web.servlets;

import by.it.academy.MK_JD2_88_2.cw1.dto.Airport;
import by.it.academy.MK_JD2_88_2.cw1.service.AirportService;
import by.it.academy.MK_JD2_88_2.cw1.service.api.IAirportService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "AirportServlet", urlPatterns = "/airports")
public class AirportServlet extends HttpServlet {

    private final IAirportService service;
    private final ObjectMapper mapper;

    public AirportServlet() {
        this.service = AirportService.getInstance();
        this.mapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        String count = req.getParameter("count");
        List<Airport> airports = this.service.get(count);

        this.mapper.writeValue(writer, airports);

    }

}
