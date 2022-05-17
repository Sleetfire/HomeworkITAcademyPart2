package by.it.academy.MK_JD2_88_2.cookie_session.service;

import by.it.academy.MK_JD2_88_2.cookie_session.dto.Person;
import by.it.academy.MK_JD2_88_2.cookie_session.service.api.IStorageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionService implements IStorageService {

    private static IStorageService instance;

    private static final String SESSION_PERSON_KEY = "person";

    private SessionService() {
    }

    @Override
    public void savePersonInSession(Person person, HttpServletRequest storage) {
        HttpSession session = storage.getSession();
        session.setAttribute(SESSION_PERSON_KEY, person);
    }

    @Override
    public Person getPersonFromSession(HttpServletRequest storage) {
        HttpSession session = storage.getSession();
        return (Person) session.getAttribute(SESSION_PERSON_KEY);
    }

    @Override
    public void savePersonInCookie(Person person, HttpServletResponse resp) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Person getPersonFromCookie(HttpServletRequest req) {
        throw new UnsupportedOperationException();
    }

    public static IStorageService getInstance() {
        if (instance == null) {
            return new SessionService();
        }
        return instance;
    }




}
