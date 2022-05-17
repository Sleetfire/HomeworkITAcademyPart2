package by.it.academy.MK_JD2_88_2.cookie_session.service;

import by.it.academy.MK_JD2_88_2.cookie_session.dto.Person;
import by.it.academy.MK_JD2_88_2.cookie_session.service.api.IStorageService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class CookieService implements IStorageService {

    private static IStorageService instance;

    private static final String FIRST_NAME_COOKIE_KEY = "firstName";
    private static final String LAST_NAME_COOKIE_KEY = "lastName";
    private static final String AGE_COOKIE_KEY = "age";

    private CookieService() {
    }

    @Override
    public void savePersonInCookie(Person person, HttpServletResponse resp) {
        String firstName = person.getFirstName();
        String lastName = person.getLastName();
        int age = person.getAge();
        saveCookie(FIRST_NAME_COOKIE_KEY, firstName, resp);
        saveCookie(LAST_NAME_COOKIE_KEY, lastName, resp);
        saveCookie(AGE_COOKIE_KEY, Integer.toString(age), resp);
    }

    @Override
    public Person getPersonFromCookie(HttpServletRequest req) {
        String firstName = getValueFromCookie(FIRST_NAME_COOKIE_KEY, req);
        String lastName = getValueFromCookie(LAST_NAME_COOKIE_KEY, req);
        int age = Integer.parseInt(getValueFromCookie(AGE_COOKIE_KEY, req));
        Person person = new Person(firstName, lastName, age);
        return person;
    }

    @Override
    public void savePersonInSession(Person person, HttpServletRequest req) {
        throw  new UnsupportedOperationException();
    }

    @Override
    public Person getPersonFromSession(HttpServletRequest req) {
        throw  new UnsupportedOperationException();
    }

    /**
     * Сохраняет куки
     * @param cookieName название куки
     * @param value значение куки
     * @param resp ответ сервера
     */
    private void saveCookie(String cookieName, String value, HttpServletResponse resp) {
        Cookie cookie = new Cookie(cookieName, URLEncoder.encode(value, StandardCharsets.UTF_8));
        cookie.setMaxAge(Math.toIntExact(TimeUnit.DAYS.toSeconds(1)));
        resp.addCookie(cookie);
    }

    /**
     * Получает значение по имени из куки
     * @param cookieName имя куки
     * @param req запрос к серверу
     * @return значение из куки
     */
    private String getValueFromCookie(String cookieName, HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (Objects.equals(cookieName, cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return ""; // вроде лучше, чем null
    }

    public static IStorageService getInstance() {
        if (instance == null) {
            return new CookieService();
        }
        return instance;
    }
}
