package by.it.academy.MK_JD2_88_2.cookie_session.service.api;

import by.it.academy.MK_JD2_88_2.cookie_session.dto.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IStorageService {

    /**
     * Сохраняет пользователя в куки
     *
     * @param person пользователь
     * @param resp   ответ сервера
     */
    void savePersonInCookie(Person person, HttpServletResponse resp);

    /**
     * Сохраняет пользователя в сессию
     *
     * @param person пользователь
     * @param req    запрос к серверу
     */
    void savePersonInSession(Person person, HttpServletRequest req);

    /**
     * Возвращает пользователя из куки
     *
     * @param req запрос к серверу
     * @return пользователь
     */
    Person getPersonFromCookie(HttpServletRequest req);

    /**
     * Возвращает пользователя из сессии
     *
     * @param req запрос к серверу
     * @return пользователь
     */
    Person getPersonFromSession(HttpServletRequest req);

}
