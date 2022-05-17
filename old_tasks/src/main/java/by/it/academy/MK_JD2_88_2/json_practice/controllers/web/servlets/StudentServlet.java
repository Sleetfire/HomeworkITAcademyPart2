package by.it.academy.MK_JD2_88_2.json_practice.controllers.web.servlets;

import by.it.academy.MK_JD2_88_2.json_practice.dto.Student;
import by.it.academy.MK_JD2_88_2.json_practice.service.StudentService;
import by.it.academy.MK_JD2_88_2.json_practice.service.api.IJsonObjectService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "StudentServlet", urlPatterns = "/students")
public class StudentServlet extends HttpServlet {

    private final IJsonObjectService<Student> service;
    private final ObjectMapper mapper;

    public StudentServlet() {
        this.service = StudentService.getInstance();
        this.mapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        List<Student> studentsList = this.service.getJsonObjectsList();

        Map<String, List<Student>> students = new HashMap<>();
        students.put("data", studentsList);

        //this.mapper.writeValue(writer, studentsList);
        this.mapper.writeValue(writer, students);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Student student = this.mapper.readValue(req.getInputStream(), Student.class);

        this.service.addJsonObjectToList(student);

    }
}
