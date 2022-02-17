package by.it.academy.MK_JD2_88_2.hw1.controllers.web.listeners;

import by.it.academy.MK_JD2_88_2.hw1.session_counter.SessionCounter;
import by.it.academy.MK_JD2_88_2.hw1.session_counter.api.ISessionCounter;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionCounterListener implements HttpSessionListener {

    private final ISessionCounter sessionCounter = SessionCounter.getInstance();

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        this.sessionCounter.doIncrementCounter();
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        this.sessionCounter.doDecrementCounter();
    }
}
