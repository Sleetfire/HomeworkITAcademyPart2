package by.it.academy.MK_JD2_88_2.open_weather.controllers.web.servlets;

import by.it.academy.MK_JD2_88_2.open_weather.OpenWeather;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "OpenWeatherServlet", urlPatterns = "/weather_now")
public class OpenWeatherServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        String cityName = req.getParameter("city");

        OpenWeather weather = new OpenWeather(cityName, "f8af55e7cdc1b97b0a26713a2918f592");
        String currentTemperature = weather.getParameter("main", "temp");
        String feelLike = weather.getParameter("main", "feels_like");
        String pressure = weather.getParameter("main", "pressure");
        String humidity = weather.getParameter("main", "humidity");
        String windSpeed = weather.getParameter("wind", "speed");

        if (currentTemperature != null) {
            writer.write("<span style='color: blue;'> <h2>In city" +  cityName + "</h2></span>");
            writer.write("<h4>Temperature: " + currentTemperature + " degrees </h4>");
            writer.write("<h4>Feels like: " + feelLike + " degrees </h4>");
            writer.write("<h4>Pressure: " + pressure + " gPa</h4>");
            writer.write("<h4>Humidity: " + humidity + " % </h4>");
            writer.write("<h4>Wind speed: " + windSpeed + " m/s </h4>");
        }
    }
}
