package by.it.academy.MK_JD2_88_2.json_practice.service;


import by.it.academy.MK_JD2_88_2.json_practice.dto.Student;
import by.it.academy.MK_JD2_88_2.json_practice.service.api.IJsonObjectService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentService implements IJsonObjectService<Student> {

    private final List<Student> studentList = new ArrayList<>();
    private static final IJsonObjectService instance = new StudentService();

    private StudentService() {

    }

    @Override
    public void addJsonObjectToList(Student jsonObject) {
        this.studentList.add(jsonObject);
    }

    @Override
    public List<Student> getJsonObjectsList() {
        return Collections.unmodifiableList(this.studentList);
    }

    public static IJsonObjectService getInstance() {
        return instance;
    }
}
